package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoFinalNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoInicialNaoPodeSerNuloException;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class PeriodoFuncionamento {

    private LocalTime horaInicial;
    private LocalTime horaFinal;

    public PeriodoFuncionamento(
            LocalTime horaInicial,
            LocalTime horaFinal) {

        if(horaInicial == null) {
            throw new HorarioFuncionamentoInicialNaoPodeSerNuloException();
        }

        if(horaFinal == null) {
            throw new HorarioFuncionamentoFinalNaoPodeSerNuloException();
        }

        if(horaInicial.isAfter(horaFinal)) {
            throw new HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException();
        }

        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;

    }

}
