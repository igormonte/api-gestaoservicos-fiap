package br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto;

import java.time.LocalDateTime;
import java.util.List;

public record IcalEventoDto(
        String titulo,
        String descricao,
        List<IcalParticipanteDto> participante,
        LocalDateTime dataInicial,
        LocalDateTime dataFinal
) {
}
