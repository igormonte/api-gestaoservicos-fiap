package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception;

public class EnderecoNaoPodeSerNuloException extends RuntimeException {

    public EnderecoNaoPodeSerNuloException() {
        super("O nome não pode se nulo!");
    }

    public EnderecoNaoPodeSerNuloException(String message) {
        super(message);
    }

}
