package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.exception;

public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException() {
        super("Field not fond!");
    }

    public FieldNotFoundException(String message) {
        super(message);
    }

}
