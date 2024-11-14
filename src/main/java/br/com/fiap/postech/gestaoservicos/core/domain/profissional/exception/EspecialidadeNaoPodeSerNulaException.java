package br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception;

public class EspecialidadeNaoPodeSerNulaException extends RuntimeException {

    public EspecialidadeNaoPodeSerNulaException() {
        super("Especialidade não pode se nula!");
    }

    public EspecialidadeNaoPodeSerNulaException(String message) {
        super(message);
    }

}
