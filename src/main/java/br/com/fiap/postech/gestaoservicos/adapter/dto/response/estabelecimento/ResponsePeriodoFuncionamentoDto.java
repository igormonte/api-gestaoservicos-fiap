package br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
import java.util.UUID;

public record ResponsePeriodoFuncionamentoDto (
    UUID id,
    LocalTime horaInicial,
    LocalTime horaFinal){

}
