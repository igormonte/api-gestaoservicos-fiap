package br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception;

public class EspecialidadeNaoEncontradaException extends RuntimeException {

    public EspecialidadeNaoEncontradaException() {
        super("Especialidade não encontrada!");
    }

    public EspecialidadeNaoEncontradaException(String message) {
        super(message);
    }

}
