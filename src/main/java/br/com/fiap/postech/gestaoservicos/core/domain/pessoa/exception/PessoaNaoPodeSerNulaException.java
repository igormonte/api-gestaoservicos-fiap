package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception;

public class PessoaNaoPodeSerNulaException extends RuntimeException {

    public PessoaNaoPodeSerNulaException() {
        super("Pessoa não pode ser nula");
    }

    public PessoaNaoPodeSerNulaException(String message) {
        super(message);
    }

}
