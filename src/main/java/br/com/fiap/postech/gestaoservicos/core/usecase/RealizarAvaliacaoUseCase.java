package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RealizarAvaliacaoUseCase {

    Agendamento executar(
            UUID idCliente,
            UUID idAgendamento,
            int nota,
            String comentario);
}
