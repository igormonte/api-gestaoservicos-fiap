package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.AgendamentoRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.AgendamentoDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.AgendamentoMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AgendamentoDbGateway implements AgendamentoRepository {

    private final AgendamentoDbRepository agendamentoDbRepository;
    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoDbGateway(
            AgendamentoDbRepository agendamentoDbRepository,
            AgendamentoMapper agendamentoMapper) {
        this.agendamentoDbRepository = agendamentoDbRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    @Override
    public Agendamento criarAgendamento(Agendamento agendamento) {
        return this.agendamentoMapper.toAgendamento(
                this.agendamentoDbRepository.save(
                        this.agendamentoMapper.toAgendamentoDb(agendamento)));
    }

    @Override
    public Agendamento buscarAgendamentoPorId(UUID id) {
        return this.agendamentoMapper.toAgendamento(
                this.agendamentoDbRepository.findById(id).orElseThrow());
    }

    @Override
    public List<Agendamento> buscarPorClienteId(UUID idCliente) {
        return this.agendamentoMapper.toAgendamentoList(
                this.agendamentoDbRepository.findByClienteIdAndDataHoraInicial(idCliente, LocalDateTime.now()));
    }

    @Override
    public List<Agendamento> buscarPorProfissionalId(UUID idProfissional) {
        return this.agendamentoMapper.toAgendamentoList(
                this.agendamentoDbRepository.findByProfissionalIdAndDataHoraInicial(
                        idProfissional,
                        LocalDateTime.now().withMinute(0).withSecond(0).withNano(0)));
    }

    @Override
    public List<Agendamento> buscarAgendamentoPorIdProfissionalEDataInicialEDataFinal(
            UUID idProfissional, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return this.agendamentoMapper.toAgendamentoList(this.agendamentoDbRepository.findByServicoProfissionalIdAndDataHoraInicialAndDataHoraFinal(
                idProfissional, dataInicial, dataFinal));
    }

    @Override
    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        return this.agendamentoMapper.toAgendamento(
                this.agendamentoDbRepository.save(
                        this.agendamentoMapper.toAgendamentoDb(agendamento)));
    }

    @Override
    public List<Agendamento> todos() {
        return this.agendamentoMapper.toAgendamentoList(
                this.agendamentoDbRepository.findAll());
    }

}
