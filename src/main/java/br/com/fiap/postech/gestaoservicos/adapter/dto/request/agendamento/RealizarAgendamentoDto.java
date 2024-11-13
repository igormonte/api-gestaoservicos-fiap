package br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public record RealizarAgendamentoDto(
        UUID idCliente,
        UUID idEstabelecimento,
        UUID idProfissional,
        UUID idEspecialidade,
        LocalDateTime dataHora
) {
}
