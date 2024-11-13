package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.data;

public enum LogicalOperator {
    AND("&&"),
    OR("||");

    private final String operator;

    LogicalOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
