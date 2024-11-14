package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception;

public class EstabelecimentoNaoPodeSerNuloException extends RuntimeException {

    public EstabelecimentoNaoPodeSerNuloException() {
        super("Estabelecimento não pode ser nulo");
    }

    public EstabelecimentoNaoPodeSerNuloException(String message) {
        super(message);
    }

}
