package br.com.fiap.postech.gestaoservicos.core.usecase.repository;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendamentoRepository {

    Agendamento criarAgendamento(Agendamento agendamento);

    Agendamento buscarAgendamentoPorId(UUID id);

    List<Agendamento> buscarPorClienteId(UUID idCliente);

    List<Agendamento> buscarPorProfissionalId(UUID idProfissional);

    List<Agendamento> buscarAgendamentoPorIdProfissionalEDataInicialEDataFinal(
            UUID idProfissional, LocalDateTime dataInicial, LocalDateTime dataFinal);

    Agendamento atualizarAgendamento(Agendamento agendamento);

    List<Agendamento> todos();
}
