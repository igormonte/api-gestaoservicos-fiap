package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception;

public class NomeNaoPodeSerNuloException extends RuntimeException {

    public NomeNaoPodeSerNuloException() {
        super("Nome não pode ser nulo");
    }

    public NomeNaoPodeSerNuloException(String message) {
        super(message);
    }

}
