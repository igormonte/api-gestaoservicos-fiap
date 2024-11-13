package br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.DiaSemana;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public record ResponseFuncionamentoDto (

    UUID id,
    DiaSemana diaSemana,
    List<ResponsePeriodoFuncionamentoDto> periodoFuncionamento){

}