package br.com.fiap.postech.gestaoservicos.infrastructure.criteriaparser;

import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.exception.DataTypeNotSupportedException;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository.ValueParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ValueParserImpl implements ValueParser {
    @Override
    public Object parseValue(String value, Class<?> supplier) {
        if(supplier.isEnum()) {
            return value;
        }
        return switch (supplier.getSimpleName()) {
            case "String" -> value;
            case "Integer" -> Integer.parseInt(value);
            case "Double" -> Double.parseDouble(value);
            case "BigDecimal" -> BigDecimal.valueOf(Double.parseDouble(value));
            case "Float" -> Float.parseFloat(value);
            case "Long" -> Long.parseLong(value);
            case "Boolean" -> Boolean.parseBoolean(value);
            case "UUID" -> UUID.fromString(value);
            case "LocalDate" -> LocalDate.parse(value);
            case "LocalDateTime" -> LocalDateTime.parse(value);
            default -> throw new DataTypeNotSupportedException("DataType not supported: " + supplier.getSimpleName());
        };
    }
}
