package br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception;

public class ProfissionalNaoEncontradoException extends RuntimeException {

    public ProfissionalNaoEncontradoException() {
        super("Profissional não encontrado!");
    }

    public ProfissionalNaoEncontradoException(String message) {
        super(message);
    }

}
