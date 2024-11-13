package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.ProfissionalDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.ProfissionalMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProfissionalDbGatewayTest {

    @Autowired
    private ProfissionalMapper profissionalMapper;
    @Mock
    private ProfissionalDbRepository profissionalDbRepository;
    private ProfissionalRepository profissionalRepository;
    private AutoCloseable openMocks;
    
    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
        this.profissionalRepository = new ProfissionalDbGateway(profissionalDbRepository, profissionalMapper);
    }
    
    @AfterEach
    void tearDown() throws Exception {
        this.openMocks.close();
    }

    @Test
    void deveCriarProfissional() {

        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        ProfissionalDbEntity profissionalDb = this.profissionalMapper.toProfissionalDbEntity(profissional);
        profissionalDb.setId(UUID.randomUUID());

        when(profissionalDbRepository.save(any(ProfissionalDbEntity.class)))
                .thenReturn(profissionalDb);

        ProfissionalEntity profissionalSalvo =
                this.profissionalRepository.salvarProfissional(profissional);

        verify(this.profissionalDbRepository, times(1))
                .save(any(ProfissionalDbEntity.class));

        assertThat(profissionalSalvo).isNotNull();
        assertThat(profissionalSalvo)
                .extracting(ProfissionalEntity::getEspecialidade)
                .isEqualTo(profissional.getEspecialidade());
        Optional.ofNullable(profissionalSalvo.getEspecialidade()).ifPresent(especialidade ->
            assertThat(IntStream.range(0,especialidade.size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> especialidade.get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(especialidade.get(i))
                            .extracting(Especialidade::getNome)
                            .isEqualTo(profissional.getEspecialidade().get(i).getNome());
                    assertThat(especialidade.get(i))
                            .extracting(Especialidade::getDescricao)
                            .isEqualTo(profissional.getEspecialidade().get(i).getDescricao());
                    assertThat(especialidade.get(i))
                            .extracting(Especialidade::getDuracao)
                            .isEqualTo(profissional.getEspecialidade().get(i).getDuracao());
                }));

        Optional.ofNullable(profissionalSalvo.getAgenda()).ifPresent(agenda ->
            assertThat(IntStream.range(0,agenda.getAgendamento().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> profissionalSalvo.getAgenda().getAgendamento().get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(profissionalSalvo.getAgenda().getAgendamento().get(i))
                            .extracting(Agendamento::getDataHora)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getDataHora());
                    assertThat(profissionalSalvo.getAgenda().getAgendamento().get(i))
                            .extracting(Agendamento::getDuracao)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getDuracao());
                    assertThat(profissionalSalvo.getAgenda().getAgendamento().get(i))
                            .extracting(Agendamento::getServico)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico());
                    assertThat(profissionalSalvo.getAgenda().getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getCliente)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getCliente());
                    assertThat(profissionalSalvo.getAgenda().getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getProfissional)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getProfissional());
                    assertThat(profissionalSalvo.getAgenda().getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getEstabelecimento)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getEstabelecimento());
                    assertThat(profissionalSalvo.getAgenda().getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getEspecialidade)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getEspecialidade());
            }));
    }

}
