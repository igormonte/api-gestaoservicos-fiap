package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.adapter.gateway.EspecialidadeDbGateway;
import br.com.fiap.postech.gestaoservicos.adapter.gateway.ProfissionalDbGateway;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.ProfissionalDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.ProfissionalMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EspecialidadeHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastrarPerfilProfissionalUseCaseIT {

    @Autowired
    private CadastrarPerfilProfissionalUseCase cadastrarPerfilProfissionalUseCase;

    @Autowired
    private EspecialidadeDbGateway especialidadeDbGateway;

    @Test
    void deveExecutar() {

        List<Especialidade> especialidadeList = List.of(this.registrarEspecialidade());
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        profissional.setId(UUID.randomUUID());

        ProfissionalEntity profissionalSalvo =
                this.cadastrarPerfilProfissionalUseCase.executar(
                        profissional.getPessoa().getNome(),
                        profissional.getPessoa().getEmail(),
                        profissional.getPessoa().getDocumento().getNumeroDocumento(),
                        profissional.getPessoa().getDocumento().getTipoDocumento(),
                        profissional.getPessoa().getDataNascimento(),
                        especialidadeList.stream().map(Especialidade::getId).collect(Collectors.toList()));

        assertThat(profissionalSalvo).isNotNull();
        assertThat(IntStream.range(0,profissionalSalvo.getEspecialidade().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> profissionalSalvo.getEspecialidade().get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(profissionalSalvo.getEspecialidade().get(i))
                            .extracting(Especialidade::getNome)
                            .isEqualTo(especialidadeList.get(i).getNome());
                    assertThat(profissionalSalvo.getEspecialidade().get(i))
                            .extracting(Especialidade::getDescricao)
                            .isEqualTo(especialidadeList.get(i).getDescricao());
                    assertThat(profissionalSalvo.getEspecialidade().get(i))
                            .extracting(Especialidade::getDuracao)
                            .isEqualTo(especialidadeList.get(i).getDuracao());
                });

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


    private Especialidade registrarEspecialidade() {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        especialidade.setId(UUID.randomUUID());

        return this.especialidadeDbGateway.criarEspecialidade(especialidade);
    }

}
