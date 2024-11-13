package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception;

public class NomeInvalidoException extends RuntimeException {

    public NomeInvalidoException() {
        super("O nome não pode se nulo!");
    }

    public NomeInvalidoException(String message) {
        super(message);
    }

}
