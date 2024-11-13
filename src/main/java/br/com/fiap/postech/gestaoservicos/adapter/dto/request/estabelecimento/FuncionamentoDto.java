package br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.DiaSemana;

import java.util.List;

public record FuncionamentoDto (
    DiaSemana diaSemana,
    List<PeriodoFuncionamentoDto> periodoFuncionamento) {

}