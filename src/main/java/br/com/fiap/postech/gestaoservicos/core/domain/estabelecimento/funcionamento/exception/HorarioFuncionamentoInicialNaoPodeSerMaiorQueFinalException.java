package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception;

public class HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException extends RuntimeException {

    public HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException() {
        super("O horário final não pode ser menor que horário inicial.");
    }

    public HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException(String message) {
        super(message);
    }

}

