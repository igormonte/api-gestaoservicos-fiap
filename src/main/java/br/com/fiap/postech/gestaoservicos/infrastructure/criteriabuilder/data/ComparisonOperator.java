package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.data;

public enum ComparisonOperator {
    EQUALS("=eq="),
    NOT_EQUALS("=ne="),
    GREATER_THAN("=gt="),
    GREATER_THAN_OR_EQUAL("=gte="),
    LESS_THAN("=lt="),
    LESS_THAN_OR_EQUAL("=lte="),
    IN("=in="),
    NOT_IN("=nin="),
    EXISTS("=exists="),
    TYPE("=type="),
    REGEX("=regex="),
    TEXT("=text="),
    NOT("=not="),
    NOR("=nor=");

    private final String operator;

    ComparisonOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static ComparisonOperator valueFrom(String operator) {
        for (ComparisonOperator comparisonOperator : ComparisonOperator.values()) {
            if (comparisonOperator.getOperator().equals(operator)) {
                return comparisonOperator;
            }
        }
        throw new IllegalArgumentException("Invalid operator: " + operator);
    }
}
