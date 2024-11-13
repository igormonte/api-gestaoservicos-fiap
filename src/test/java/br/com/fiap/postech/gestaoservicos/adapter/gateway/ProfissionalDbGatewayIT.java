package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfissionalDbGatewayIT {
    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Test
    void deveCriarProfissional() {

        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        profissional.setId(UUID.randomUUID());

        ProfissionalEntity profissionalSalvo =
                this.profissionalRepository.salvarProfissional(profissional);

        assertThat(profissionalSalvo).isNotNull();
        assertThat(profissionalSalvo)
                .extracting(ProfissionalEntity::getEspecialidade)
                .isEqualTo(profissional.getEspecialidade());
        Optional.ofNullable(profissionalSalvo.getEspecialidade()).ifPresent(especialidade ->
            assertThat(IntStream.range(0,especialidade.size()).boxed().collect(
                    toMap(
                            idx -> idx,
                            idx -> especialidade.get(idx))))
                    .allSatisfy((i, idx)->{
                        assertThat(especialidade.get(i))
                                .extracting(Especialidade::getNome)
                                .isEqualTo(profissional.getEspecialidade().get(i).getNome());
                        assertThat(especialidade.get(i))
                                .extracting(Especialidade::getDescricao)
                                .isEqualTo(profissional.getEspecialidade().get(i).getDescricao());
                    }));

        Optional.ofNullable(profissionalSalvo.getAgenda()).ifPresent(agenda ->
        assertThat(IntStream.range(0,agenda.getAgendamento().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> agenda.getAgendamento().get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(agenda.getAgendamento().get(i))
                            .extracting(Agendamento::getDataHora)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getDataHora());
                    assertThat(agenda.getAgendamento().get(i))
                            .extracting(Agendamento::getDuracao)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getDuracao());
                    assertThat(agenda.getAgendamento().get(i))
                            .extracting(Agendamento::getServico)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico());
                    assertThat(agenda.getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getCliente)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getCliente());
                    assertThat(agenda.getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getProfissional)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getProfissional());
                    assertThat(agenda.getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getEstabelecimento)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getEstabelecimento());
                    assertThat(agenda.getAgendamento().get(i).getServico())
                            .extracting(ServicoEntity::getEspecialidade)
                            .isEqualTo(profissional.getAgenda().getAgendamento().get(i).getServico().getEspecialidade());
                }));
    }

}
