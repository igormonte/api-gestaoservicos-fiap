package br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception;

public class EspecialidadeJaExisteException extends RuntimeException {

    public EspecialidadeJaExisteException() {
        super("A especialidade já existe!");
    }

    public EspecialidadeJaExisteException(String message) {
        super(message);
    }

}
