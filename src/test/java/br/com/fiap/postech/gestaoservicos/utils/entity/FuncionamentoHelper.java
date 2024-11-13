package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.FuncionamentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.PeriodoFuncionamentoDto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.DiaSemana;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.PeriodoFuncionamento;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class FuncionamentoHelper {
    public static Funcionamento getFuncionamento() {
        List<PeriodoFuncionamento> periodos = new LinkedList<>();
        periodos.add(new PeriodoFuncionamento(
                LocalTime.of(8, 0),
                LocalTime.of(23, 59)
        ));
        return new Funcionamento(
                DiaSemana.SEGUNDA,
                periodos
        );
    }

    public static List<Funcionamento> getFuncionamentoList() {
        List<PeriodoFuncionamento> periodos = new LinkedList<>();
        periodos.add(new PeriodoFuncionamento(
                LocalTime.of(8, 0),
                LocalTime.of(23, 59)
        ));
        return List.of(
            new Funcionamento(
                DiaSemana.SEGUNDA,
                periodos),
            new Funcionamento(
                DiaSemana.TERCA,
                periodos),
            new Funcionamento(
                DiaSemana.QUARTA,
                periodos),
            new Funcionamento(
                DiaSemana.QUINTA,
                periodos),
            new Funcionamento(
                DiaSemana.SEXTA,
                periodos),
            new Funcionamento(
                DiaSemana.SABADO,
                periodos),
            new Funcionamento(
                DiaSemana.DOMINGO,
                periodos)
        );
    }
    public static FuncionamentoDto getFuncionamentoDto() {
        List<PeriodoFuncionamentoDto> periodos = new LinkedList<>();
        periodos.add(new PeriodoFuncionamentoDto(
                LocalTime.of(8, 0),
                LocalTime.of(23, 59)
        ));
        return new FuncionamentoDto(
                DiaSemana.SEGUNDA,
                periodos
        );
    }
}
