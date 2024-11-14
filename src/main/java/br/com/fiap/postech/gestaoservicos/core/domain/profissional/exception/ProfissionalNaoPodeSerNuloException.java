package br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception;

public class ProfissionalNaoPodeSerNuloException extends RuntimeException {

    public ProfissionalNaoPodeSerNuloException() {
        super("Profissional não pode ser nulo");
    }

    public ProfissionalNaoPodeSerNuloException(String message) {
        super(message);
    }

}
