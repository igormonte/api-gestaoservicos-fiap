package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception;

public class EstabelecimentoNaoEncontradoException extends RuntimeException {

    public EstabelecimentoNaoEncontradoException() {
        super("Estabelecimento não encontrado!");
    }

    public EstabelecimentoNaoEncontradoException(String message) {
        super(message);
    }

}
