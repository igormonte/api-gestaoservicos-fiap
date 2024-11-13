package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception;

public class HorarioFuncionamentoFinalNaoPodeSerNuloException extends RuntimeException {

    public HorarioFuncionamentoFinalNaoPodeSerNuloException() {
        super("O horário final não pode ser nulo.");
    }

    public HorarioFuncionamentoFinalNaoPodeSerNuloException(String message) {
        super(message);
    }

}

