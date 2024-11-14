package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception;

public class DataNascimentoNaoPodeSerNulaException extends RuntimeException {

    public DataNascimentoNaoPodeSerNulaException() {
        super("Data de Nascimento não pode ser nula");
    }

    public DataNascimentoNaoPodeSerNulaException(String message) {
        super(message);
    }

}
