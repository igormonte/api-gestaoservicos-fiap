package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.AgendamentoRepository;

import java.util.List;
import java.util.UUID;

public class BuscaAgendamentoUseCaseImpl implements BuscaAgendamentoUseCase {

    private final AgendamentoRepository agendamentoRepository;

    public BuscaAgendamentoUseCaseImpl(
            AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @Override
    public List<Agendamento> buscarPorClienteId(UUID idCliente) {
        return this.agendamentoRepository.buscarPorClienteId(idCliente);
    }

    @Override
    public List<Agendamento> buscarPorProfissionalId(UUID idProfissional){
        return this.agendamentoRepository.buscarPorProfissionalId(idProfissional);
    }

    @Override
    public List<Agendamento> todos() {
        return this.agendamentoRepository.todos();
    }
}
