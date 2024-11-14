package br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception;

public class AgendamentoNaoEncontradoException extends RuntimeException {

    public AgendamentoNaoEncontradoException() {
        super("Profissional não encontrado!");
    }

    public AgendamentoNaoEncontradoException(String message) {
        super(message);
    }

}
