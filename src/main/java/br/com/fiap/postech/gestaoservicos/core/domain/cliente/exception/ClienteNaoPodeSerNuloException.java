package br.com.fiap.postech.gestaoservicos.core.domain.cliente.exception;

public class ClienteNaoPodeSerNuloException extends RuntimeException {

    public ClienteNaoPodeSerNuloException() {
        super("Cliente não pode ser nulo");
    }

    public ClienteNaoPodeSerNuloException(String message) {
        super(message);
    }

}
