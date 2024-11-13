package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.adapter.gateway.AgendamentoDbGateway;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.AgendamentoRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendamentoDb;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.AgendamentoDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.ClienteDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.EstabelecimentoDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.AgendamentoMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.AgendamentoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RealizarAgendamentoUseCaseTest {

    @Autowired
    private AgendamentoMapper agendamentoMapper;
    @Mock
    private AgendamentoDbRepository agendamentoDbRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;
    private AgendamentoRepository agendamentoRepository;
    private RealizarAgendamentoUseCase realizarAgendamentoUseCase;
    private AutoCloseable openMocks;
    
    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
        this.agendamentoRepository = new AgendamentoDbGateway(agendamentoDbRepository, agendamentoMapper);
        this.realizarAgendamentoUseCase =
                new RealizarAgendamentoUseCaseImpl(agendamentoRepository, clienteRepository, estabelecimentoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.openMocks.close();
    }

    @Test
    void deveExecutar() {

        Agendamento agendamento = AgendamentoHelper.getAgendamento();
        agendamento.getServico().getCliente().setId(UUID.randomUUID());
        agendamento.getServico().getProfissional().setId(UUID.randomUUID());
        agendamento.getServico().getEspecialidade().setId(UUID.randomUUID());
        agendamento.getServico().getEstabelecimento().setId(UUID.randomUUID());

        AgendamentoDb agendamentoDb = this.agendamentoMapper.toAgendamentoDb(agendamento);
        agendamentoDb.setId(UUID.randomUUID());

        when(agendamentoDbRepository.save(any(AgendamentoDb.class)))
                .thenReturn(agendamentoDb);

        when(clienteRepository.buscarPorId(any(UUID.class)))
                .thenReturn(agendamento.getServico().getCliente());

        when(estabelecimentoRepository.buscarPorId(any(UUID.class)))
                .thenReturn(agendamento.getServico().getEstabelecimento());

        when(estabelecimentoRepository.buscarPorId(any(UUID.class)))
                .thenReturn(agendamento.getServico().getEstabelecimento());

        Agendamento agendamentoSalvo =
                this.realizarAgendamentoUseCase.executar(
                        agendamento.getServico().getCliente().getId(),
                        agendamento.getServico().getEstabelecimento().getId(),
                        agendamento.getServico().getProfissional().getId(),
                        agendamento.getServico().getProfissional().getEspecialidade()
                                .stream().findAny().get().getId(),
                        agendamento.getDataHora()
                );

        verify(this.agendamentoDbRepository, times(1))
                .save(any(AgendamentoDb.class));

        assertThat(agendamentoSalvo)
                .isNotNull()
                .isInstanceOf(Agendamento.class);

        assertThat(agendamentoSalvo)
                .extracting(Agendamento::getId)
                .isNotNull();

        assertThat(agendamentoSalvo)
                .isNotNull()
                .extracting(Agendamento::getDataHora)
                .isEqualTo(agendamento.getDataHora());

        assertThat(agendamentoSalvo.getServico().getCliente())
                .extracting(ClienteEntity::getId)
                .isEqualTo(agendamento.getServico().getCliente().getId());

        assertThat(agendamentoSalvo.getServico().getEspecialidade())
                .extracting(Especialidade::getId)
                .isEqualTo(agendamento.getServico().getEspecialidade().getId());

        assertThat(agendamentoSalvo.getServico().getEstabelecimento())
                .extracting(EstabelecimentoEntity::getId)
                .isEqualTo(agendamento.getServico().getEstabelecimento().getId());

        assertThat(agendamentoSalvo.getServico().getProfissional())
                .extracting(ProfissionalEntity::getId)
                .isEqualTo(agendamento.getServico().getProfissional().getId());

    }
}
