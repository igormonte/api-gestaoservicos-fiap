package br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto;

import java.util.List;

public record IcalCalendarioDto(
        List<IcalEventoDto> evento
) {
}
