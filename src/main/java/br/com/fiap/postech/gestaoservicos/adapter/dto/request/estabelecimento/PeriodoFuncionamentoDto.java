package br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
import java.util.UUID;

public record PeriodoFuncionamentoDto (
    LocalTime horaInicial,
    LocalTime horaFinal) {

}
