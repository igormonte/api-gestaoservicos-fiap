package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RealizarAgendamentoUseCase {

    Agendamento executar(
            UUID idCliente,
            UUID idEstabelecimento,
            UUID idProfissional,
            UUID idEspecialidade,
            LocalDateTime dataHora);

}
