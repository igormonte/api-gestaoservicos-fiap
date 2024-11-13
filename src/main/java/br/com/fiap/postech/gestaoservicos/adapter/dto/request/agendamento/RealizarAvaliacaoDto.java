package br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento;

import java.util.UUID;

public record RealizarAvaliacaoDto(
        UUID idCliente,
        UUID idAgendamento,
        Integer nota,
        String comentario
) {
}
