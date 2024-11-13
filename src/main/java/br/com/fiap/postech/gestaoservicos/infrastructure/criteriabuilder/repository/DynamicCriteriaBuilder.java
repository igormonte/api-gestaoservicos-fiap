package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.CriteriaContext;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.data.ClassMap;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.data.ComparisonOperator;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicCriteriaBuilder<T> implements DynamicCriteria<T> {
    private final CriteriaContext context;
    private final ValueParser valueParser;
    public final ClassMap classMap;

    public DynamicCriteriaBuilder(Class<T> supplier, CriteriaContext context, ValueParser valueParser) {
        this.context = context;
        this.classMap = new ClassMap(supplier);
        this.valueParser = valueParser;
        this.read();
    }

    private void read() {

        Field[] fields = this.classMap.getClazz().getDeclaredFields();

        for(Field field: fields) {
            this.classMap.addField(field.getName(), field.getType());
            this.read(field.getName(), field);

        }
    }

    private void read(String parentName, Field field) {
        Class<?> clazz = field.getType();

        if(clazz.isPrimitive()) {
            return;
        }

        if(clazz.isEnum()) {
            return;
        }

        if(clazz.isArray()) {
            clazz = clazz.getComponentType();
        }

        if (clazz == List.class) {
            ParameterizedType type = (ParameterizedType) field.getGenericType();
            clazz = (Class<?>) type.getActualTypeArguments()[0];
        }

        String packageName = this.context.getPackageName();
        String className = clazz.getName();
        if(!className.startsWith(packageName)){
            return;
        }

        if(this.classMap.hasClass(clazz)) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();

        for(Field innerField: fields) {
            String name = String.format("%s.%s", parentName, innerField.getName());
            if(!this.classMap.hasFieldPart(name)){
                this.classMap.addField(name, innerField.getType());
                this.read(name, innerField);
            }

        }
    }

    private Criteria buildExpression(String spec) {

        Pattern pattern = Pattern.compile("(.*)(=.*=)(.*)");
        Matcher matcher = pattern.matcher(spec);

        if (!matcher.find()) {
            return null;
        }

        String field = matcher.group(1);
        String operator = matcher.group(2);
        String value = matcher.group(3);

        if(!classMap.hasField(field)) {
            throw new RuntimeException(String.format("Field %s not found", field));
        }

        ComparisonOperator comparisonOperator = ComparisonOperator.valueFrom(operator);
        Object parsedValue = valueParser.parseValue(value, classMap.getField(field));

        return switch (comparisonOperator) {
            case NOT_EQUALS -> Criteria.where(field).ne(parsedValue);
            case GREATER_THAN -> Criteria.where(field).gt(parsedValue);
            case GREATER_THAN_OR_EQUAL -> Criteria.where(field).gte(parsedValue);
            case LESS_THAN -> Criteria.where(field).lt(parsedValue);
            case LESS_THAN_OR_EQUAL -> Criteria.where(field).lte(parsedValue);
            case IN -> Criteria.where(field).in(parsedValue);
            case NOT_IN -> Criteria.where(field).nin(parsedValue);
            case EXISTS -> Criteria.where(field).exists(Boolean.parseBoolean(value));
            case REGEX -> Criteria.where(field).regex(value);
            default -> Criteria.where(field).is(parsedValue);
        };

    }

    private List<String> breakOrSpec(String spec) {
        return List.of(spec.split("\\|\\|"));
    }

    private List<String> breakAndSpec(String spec) {
        return List.of(spec.split("&&"));
    }

    private Criteria buildAndExpression(String spec) {
        List<String> andLogicalDefinitionList = breakAndSpec(spec);

        if(andLogicalDefinitionList.size()==1) {
            return buildExpression(spec);
        }

        List<Criteria> andDefinition = new LinkedList<>();

        for(String andLogicalDefinition: andLogicalDefinitionList) {
            andDefinition.add(buildExpression(andLogicalDefinition));
        }

        return new Criteria().andOperator(
            andDefinition
        );
    }

    private Criteria buildOrExpression(String spec) {
        List<String> orLogicalDefinitionList = breakOrSpec(spec);

        List<Criteria> orDefinition = new LinkedList<>();

        if(orLogicalDefinitionList.size()==1) {
            return buildExpression(spec);
        }

        for(String orLogicalDefinition: orLogicalDefinitionList) {

            Criteria andCriteria = buildAndExpression(orLogicalDefinition);

            if(andCriteria != null) {
                orDefinition.add(andCriteria);
            } else {
                orDefinition.add(buildExpression(orLogicalDefinition));
            }
        }

        return new Criteria().orOperator(
                orDefinition
        );
    }

    @Override
    public Query build(String spec) {

        Query query = new Query();

        List<String> orLogicalPaths = breakOrSpec(spec);

        if(orLogicalPaths.size() > 1) {

            Criteria orCriteria = buildOrExpression(spec);

            if(orCriteria != null) {
                query.addCriteria(orCriteria);
            }

        } else {

            List<String> andLogicalPaths = breakAndSpec(spec);

            if(andLogicalPaths.size() > 1) {

                Criteria andCriteria = buildAndExpression(spec);

                if(andCriteria != null) {
                    query.addCriteria(andCriteria);
                }
            } else {

                Criteria andCriteria = buildExpression(spec);

                if(andCriteria != null) {
                    query.addCriteria(andCriteria);
                }

            }
        }

        return query;

    }
}
