package br.com.fiap.postech.gestaoservicos.core.domain.servico.exception;

public class NotaInvalidaException extends RuntimeException {

    public NotaInvalidaException() {
        super("A nota especificada deve estar dentro do intervalo de 0 a 10.");
    }

    public NotaInvalidaException(String message) {
        super(message);
    }

}
