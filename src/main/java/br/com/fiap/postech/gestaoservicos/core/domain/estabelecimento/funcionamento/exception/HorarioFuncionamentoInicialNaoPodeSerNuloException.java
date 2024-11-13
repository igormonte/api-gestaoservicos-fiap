package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception;

public class HorarioFuncionamentoInicialNaoPodeSerNuloException extends RuntimeException {

    public HorarioFuncionamentoInicialNaoPodeSerNuloException() {
        super("O horário inicial não pode ser nulo.");
    }

    public HorarioFuncionamentoInicialNaoPodeSerNuloException(String message) {
        super(message);
    }

}

