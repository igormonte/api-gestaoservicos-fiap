package br.com.fiap.postech.gestaoservicos.core.domain.cliente.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException() {
        super("Cliente não encontrado!");
    }

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }

}
