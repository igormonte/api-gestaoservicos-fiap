package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;

import java.util.List;
import java.util.UUID;

public interface BuscaAgendamentoUseCase {

    List<Agendamento> buscarPorClienteId(UUID idCliente);

    List<Agendamento> buscarPorProfissionalId(UUID idProfissional);

    List<Agendamento> todos();
}
