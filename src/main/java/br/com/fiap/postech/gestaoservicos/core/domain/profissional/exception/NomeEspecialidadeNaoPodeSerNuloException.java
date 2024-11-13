package br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception;

public class NomeEspecialidadeNaoPodeSerNuloException extends RuntimeException {

    public NomeEspecialidadeNaoPodeSerNuloException() {
        super("O nome não pode se nulo!");
    }

    public NomeEspecialidadeNaoPodeSerNuloException(String message) {
        super(message);
    }

}
