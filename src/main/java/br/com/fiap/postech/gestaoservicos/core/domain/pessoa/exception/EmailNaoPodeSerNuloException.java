package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception;

public class EmailNaoPodeSerNuloException extends RuntimeException {

    public EmailNaoPodeSerNuloException() {
        super("Email não pode ser nulo");
    }

    public EmailNaoPodeSerNuloException(String message) {
        super(message);
    }

}
