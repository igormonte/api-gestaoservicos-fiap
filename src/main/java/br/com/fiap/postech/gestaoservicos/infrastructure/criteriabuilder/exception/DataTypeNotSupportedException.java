package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.exception;

public class DataTypeNotSupportedException extends RuntimeException {

    public DataTypeNotSupportedException() {
        super("DataType not supported!");
    }

    public DataTypeNotSupportedException(String message) {
        super(message);
    }

}
