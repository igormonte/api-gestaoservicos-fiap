package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agenda;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.AgendamentoRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RealizarAgendamentoUseCaseImpl implements RealizarAgendamentoUseCase {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public RealizarAgendamentoUseCaseImpl(
            AgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository,
            EstabelecimentoRepository estabelecimentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    public Agendamento executar(
            UUID idCliente,
            UUID idEstabelecimento,
            UUID idProfissional,
            UUID idEspecialidade,
            LocalDateTime dataHora) {
        Optional<ClienteEntity> cliente =
                Optional.ofNullable(
                        this.clienteRepository.buscarPorId(idCliente));

        if(cliente.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Optional<EstabelecimentoEntity>  estabelecimento =
                Optional.ofNullable(
                        this.estabelecimentoRepository.buscarPorId(idEstabelecimento));

        if(estabelecimento.isEmpty()) {
            throw new RuntimeException("Estabelecimento não encontrado");
        }

        Optional<ProfissionalEntity> profissional =
                estabelecimento.get().getProfissional().stream()
                        .filter(p -> p.getId().equals(idProfissional)).findAny();

        if(profissional.isEmpty()) {
            throw new RuntimeException("Profissional não encontrado");
        }

        Especialidade especialidade = profissional.get().getEspecialidade().stream()
                .filter(e -> e.getId().equals(idEspecialidade)).findAny()
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));

        Agenda agenda = new Agenda(
                this.agendamentoRepository.buscarAgendamentoPorIdProfissionalEDataInicialEDataFinal(
                    idProfissional,
                    dataHora.withHour(0).withMinute(0).withSecond(0).withNano(0),
                    dataHora.withHour(23).withMinute(59).withSecond(59).withNano(999999999)
        ));

        Agendamento agendamento = Agendamento.criaAgendamento(
                dataHora, estabelecimento.get(), cliente.get(), profissional.get(), especialidade);

        return this.agendamentoRepository.criarAgendamento(agendamento);
    }

//    @Override
//    public Agendamento executar(Agendamento agendamento) {
//        return this.agendamentoRepository.criarAgendamento(agendamento);
//    }

}
