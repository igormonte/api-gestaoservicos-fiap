package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento.RealizarAgendamentoDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class RealizarAgendamentoHelper {

    public static RealizarAgendamentoDto getRealizarAgendamentoDto() {
        return new RealizarAgendamentoDto(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.now()
        );
    }
}
