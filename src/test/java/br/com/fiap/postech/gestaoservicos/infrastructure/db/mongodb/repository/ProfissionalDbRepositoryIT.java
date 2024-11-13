package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;


import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.*;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.ProfissionalMapper;
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
import static org.assertj.core.api.Fail.fail;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfissionalDbRepositoryIT {

    @Autowired
    private ProfissionalDbRepository profissionalDbRepository;

    @Autowired
    private ProfissionalMapper profissionalMapper;

    @Test
    void deveSalvarProfissional() {
        UUID id = UUID.randomUUID();
        ProfissionalDbEntity profissionalDb = this.profissionalMapper.toProfissionalDbEntity(
            ProfissionalHelper.getProfissional()
        );
        profissionalDb.setId(id);

        ProfissionalDbEntity profissionalDbSalvo = this.profissionalDbRepository.save(profissionalDb);

        assertThat(profissionalDbSalvo)
                .isNotNull();

        assertThat(profissionalDbSalvo)
                .isNotNull()
                .extracting(ProfissionalDbEntity::getId)
                .isEqualTo(id);

        assertThat(profissionalDbSalvo)
                .extracting(ProfissionalDbEntity::getNome)
                .isEqualTo(profissionalDb.getNome());

        assertThat(profissionalDbSalvo)
                .extracting(ProfissionalDbEntity::getEmail)
                .isEqualTo(profissionalDb.getEmail());

        assertThat(profissionalDbSalvo)
                .extracting(ProfissionalDbEntity::getNumeroDocumento)
                .isEqualTo(profissionalDb.getNumeroDocumento());

        assertThat(profissionalDbSalvo)
                .isNotNull()
                .extracting(ProfissionalDbEntity::getTipoDocumento)
                .isEqualTo(profissionalDb.getTipoDocumento());

        assertThat(profissionalDbSalvo)
                .isNotNull()
                .extracting(ProfissionalDbEntity::getDataNascimento)
                .isEqualTo(profissionalDb.getDataNascimento());

        assertThat(profissionalDbSalvo)
                .extracting(ProfissionalDbEntity::getEspecialidade)
                .isEqualTo(profissionalDb.getEspecialidade());
        Optional.ofNullable(profissionalDbSalvo.getEspecialidade()).ifPresent(especialidade ->
            assertThat(IntStream.range(0,especialidade.size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> especialidade.get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(especialidade.get(i))
                            .extracting(EspecialidadeDb::getNome)
                            .isEqualTo(profissionalDb.getEspecialidade().get(i).getNome());
                    assertThat(especialidade.get(i))
                            .extracting(EspecialidadeDb::getDescricao)
                            .isEqualTo(profissionalDb.getEspecialidade().get(i).getDescricao());
                    assertThat(especialidade.get(i))
                            .extracting(EspecialidadeDb::getDuracao)
                            .isEqualTo(profissionalDb.getEspecialidade().get(i).getDuracao());
                }));
        Optional.ofNullable(profissionalDbSalvo.getAgenda()).ifPresent(agenda ->
            assertThat(IntStream.range(0,agenda.getAgendamento().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> profissionalDbSalvo.getAgenda().getAgendamento().get(idx
                        ))))
            .allSatisfy((i, idx)->{
                assertThat(profissionalDbSalvo.getAgenda().getAgendamento().get(i))
                        .extracting(AgendamentoDb::getDataHora)
                        .isEqualTo(profissionalDb.getAgenda().getAgendamento().get(i).getDataHora());
                assertThat(profissionalDbSalvo.getAgenda().getAgendamento().get(i))
                        .extracting(AgendamentoDb::getDuracao)
                        .isEqualTo(profissionalDb.getAgenda().getAgendamento().get(i).getDuracao());
                assertThat(profissionalDbSalvo.getAgenda().getAgendamento().get(i))
                        .extracting(AgendamentoDb::getServico)
                        .isEqualTo(profissionalDb.getAgenda().getAgendamento().get(i).getServico());
                assertThat(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getCliente)
                        .isEqualTo(profissionalDb.getAgenda().getAgendamento().get(i).getServico().getCliente());
                assertThat(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getEspecialidade)
                        .isEqualTo(profissionalDb.getAgenda().getAgendamento().get(i).getServico().getEspecialidade());
                assertThat(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getEstabelecimento)
                        .isEqualTo(profissionalDb.getAgenda().getAgendamento().get(i).getServico().getEstabelecimento());
                assertThat(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getProfissional)
                        .isEqualTo(profissionalDb.getAgenda().getAgendamento().get(i).getServico().getProfissional());
        }));
    }

    void deveBuscarProfissionalPorId() {
        ProfissionalDbEntity profissionalDbSalvo = this.registrarProfissional();
        UUID id = profissionalDbSalvo.getId();

        Optional<ProfissionalDbEntity> profissionalDbBuscado =
                this.profissionalDbRepository.findById(id);

        assertThat(profissionalDbBuscado)
                .isPresent()
                .containsSame(profissionalDbSalvo);
        profissionalDbBuscado.ifPresent(profissionalDb -> {
            assertThat(profissionalDb)
                    .isNotNull()
                    .extracting(ProfissionalDbEntity::getId)
                    .isEqualTo(id);

            assertThat(profissionalDb)
                    .extracting(ProfissionalDbEntity::getNome)
                    .isEqualTo(profissionalDbSalvo.getNome());

            assertThat(profissionalDb)
                    .extracting(ProfissionalDbEntity::getEmail)
                    .isEqualTo(profissionalDb.getEmail());


            assertThat(profissionalDb)
                    .extracting(ProfissionalDbEntity::getNumeroDocumento)
                    .isEqualTo(profissionalDbSalvo.getNumeroDocumento());

            assertThat(profissionalDb)
                    .isNotNull()
                    .extracting(ProfissionalDbEntity::getTipoDocumento)
                    .isEqualTo(profissionalDbSalvo.getTipoDocumento());

            assertThat(profissionalDb)
                    .isNotNull()
                    .extracting(ProfissionalDbEntity::getDataNascimento)
                    .isEqualTo(profissionalDbSalvo.getDataNascimento());

            assertThat(profissionalDb)
                    .extracting(ProfissionalDbEntity::getEspecialidade)
                    .isEqualTo(profissionalDbSalvo.getEspecialidade());
            assertThat(IntStream.range(0,profissionalDb.getEspecialidade().size()).boxed().collect(
                    toMap(
                            idx -> idx,
                            idx -> profissionalDb.getEspecialidade().get(idx
                            ))))
                    .allSatisfy((i, idx)->{
                        assertThat(profissionalDb.getEspecialidade().get(i))
                                .extracting(EspecialidadeDb::getNome)
                                .isEqualTo(profissionalDbSalvo.getEspecialidade().get(i).getNome());
                        assertThat(profissionalDb.getEspecialidade().get(i))
                                .extracting(EspecialidadeDb::getDescricao)
                                .isEqualTo(profissionalDbSalvo.getEspecialidade().get(i).getDescricao());
                        assertThat(profissionalDb.getEspecialidade().get(i))
                                .extracting(EspecialidadeDb::getDuracao)
                                .isEqualTo(profissionalDbSalvo.getEspecialidade().get(i).getDuracao());
                    });

            assertThat(IntStream.range(0,profissionalDb.getAgenda().getAgendamento().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> profissionalDb.getAgenda().getAgendamento().get(idx
                        ))))
            .allSatisfy((i, idx)->{
                assertThat(profissionalDb.getAgenda().getAgendamento().get(i))
                        .extracting(AgendamentoDb::getDataHora)
                        .isEqualTo(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getDataHora());
                assertThat(profissionalDb.getAgenda().getAgendamento().get(i))
                        .extracting(AgendamentoDb::getDuracao)
                        .isEqualTo(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getDuracao());
                assertThat(profissionalDb.getAgenda().getAgendamento().get(i))
                        .extracting(AgendamentoDb::getServico)
                        .isEqualTo(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico());
                assertThat(profissionalDb.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getCliente)
                        .isEqualTo(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico().getCliente());
                assertThat(profissionalDb.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getEspecialidade)
                        .isEqualTo(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico().getEspecialidade());
                assertThat(profissionalDb.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getEstabelecimento)
                        .isEqualTo(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico().getEstabelecimento());
                assertThat(profissionalDb.getAgenda().getAgendamento().get(i).getServico())
                        .extracting(ServicoDbEntity::getProfissional)
                        .isEqualTo(profissionalDbSalvo.getAgenda().getAgendamento().get(i).getServico().getProfissional());
            });
        });

        fail("Teste não implementado");}

    void deveBuscarProfissionalPorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    @Test
    void deveDeletarProfissional() {
        ProfissionalDbEntity profissionalDbSalvo = this.registrarProfissional();
        UUID id = profissionalDbSalvo.getId();

        this.profissionalDbRepository.deleteById(id);
        Optional<ProfissionalDbEntity> profissionalBuscado = this.profissionalDbRepository.findById(id);

        assertThat(profissionalBuscado)
                .isEmpty();
    }

    private ProfissionalDbEntity registrarProfissional() {
        UUID id = UUID.randomUUID();
        ProfissionalDbEntity profissionalDb = this.profissionalMapper.toProfissionalDbEntity(
                ProfissionalHelper.getProfissional()
        );
        profissionalDb.setId(id);

        return this.profissionalDbRepository.save(profissionalDb);
    }


}
