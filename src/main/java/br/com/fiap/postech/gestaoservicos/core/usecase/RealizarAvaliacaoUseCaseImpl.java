package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.exception.ClienteNaoEncontradoException;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.AgendamentoNaoEncontradoException;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.Avaliacao;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.AgendamentoRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;

import java.util.Optional;
import java.util.UUID;

public class RealizarAvaliacaoUseCaseImpl implements RealizarAvaliacaoUseCase {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;

    public RealizarAvaliacaoUseCaseImpl(
            AgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Agendamento executar(
            UUID idCliente,
            UUID idAgendamento,
            int nota,
            String comentario) {
        Optional<ClienteEntity> cliente =
                Optional.ofNullable(
                        this.clienteRepository.buscarPorId(idCliente));

        if(cliente.isEmpty()) {
            throw new ClienteNaoEncontradoException();
        }

        Optional<Agendamento>  agendamento =
                Optional.ofNullable(
                        this.agendamentoRepository.buscarAgendamentoPorId(idAgendamento));

        if(agendamento.isEmpty()) {
            throw new AgendamentoNaoEncontradoException();
        }

        Agendamento agendamentoSalvo = agendamento.get();

        agendamentoSalvo.getServico().setAvaliacao(
                new Avaliacao(nota, comentario));

        return this.agendamentoRepository.atualizarAgendamento(agendamentoSalvo);
    }

}
