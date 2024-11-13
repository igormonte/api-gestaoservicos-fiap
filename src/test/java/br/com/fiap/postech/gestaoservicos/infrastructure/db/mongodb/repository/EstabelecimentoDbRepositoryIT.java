package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.*;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.EstabelecimentoMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EstabelecimentoHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;


@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstabelecimentoDbRepositoryIT {

    @Autowired
    private EstabelecimentoDbRepository estabelecimentoDbRepository;
    
    @Autowired
    private EstabelecimentoMapper estabelecimentoMapper;

    @Test
    void deveSalvarEstabelecimento() {
        UUID id = UUID.randomUUID();

        EstabelecimentoDbEntity estabelecimentoDb = this.estabelecimentoMapper.toEstabelecimentoDbEntity(
                EstabelecimentoHelper.getEstabelecimento()
        );
        estabelecimentoDb.setId(id);

        EstabelecimentoDbEntity estabelecimentoDbSalvo = this.estabelecimentoDbRepository.save(estabelecimentoDb);

        assertThat(estabelecimentoDbSalvo)
                .isNotNull();

        assertThat(estabelecimentoDbSalvo)
                .isNotNull()
                .extracting(EstabelecimentoDbEntity::getId)
                .isEqualTo(id);
        
        assertThat(estabelecimentoDbSalvo)
                .isNotNull()
                .extracting(EstabelecimentoDbEntity::getNome)
                .isEqualTo(EstabelecimentoHelper.getEstabelecimento().getNome());
        assertThat(estabelecimentoDbSalvo)
                .extracting(EstabelecimentoDbEntity::getNome)
                .isEqualTo(estabelecimentoDb.getNome());
        assertThat(estabelecimentoDbSalvo.getEndereco())
                .extracting(EnderecoDb::getRua)
                .isEqualTo(estabelecimentoDb.getEndereco().getRua());
        assertThat(estabelecimentoDbSalvo.getEndereco())
                .extracting(EnderecoDb::getNumero)
                .isEqualTo(estabelecimentoDb.getEndereco().getNumero());
        assertThat(estabelecimentoDbSalvo.getEndereco())
                .extracting(EnderecoDb::getBairro)
                .isEqualTo(estabelecimentoDb.getEndereco().getBairro());
        assertThat(estabelecimentoDbSalvo.getEndereco())
                .extracting(EnderecoDb::getCidade)
                .isEqualTo(estabelecimentoDb.getEndereco().getCidade());
        assertThat(estabelecimentoDbSalvo.getEndereco())
                .extracting(EnderecoDb::getEstado)
                .isEqualTo(estabelecimentoDb.getEndereco().getEstado());
        assertThat(estabelecimentoDbSalvo.getEndereco())
                .extracting(EnderecoDb::getCep)
                .isEqualTo(estabelecimentoDb.getEndereco().getCep());
        assertThat(IntStream.range(0,estabelecimentoDbSalvo.getFoto().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> estabelecimentoDbSalvo.getFoto().get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(estabelecimentoDbSalvo.getFoto().get(i))
                            .isEqualTo(estabelecimentoDb.getFoto().get(i));
                });
        assertThat(IntStream.range(0,estabelecimentoDbSalvo.getProfissional().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> estabelecimentoDbSalvo.getProfissional().get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i))
                            .extracting(ProfissionalDbEntity::getNome)
                            .isEqualTo(estabelecimentoDb.getProfissional().get(i).getNome());
 
                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i))
                            .extracting(ProfissionalDbEntity::getEmail)
                            .isEqualTo(estabelecimentoDb.getProfissional().get(i).getEmail());

                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i))
                            .extracting(ProfissionalDbEntity::getTipoDocumento)
                            .isEqualTo(estabelecimentoDb.getProfissional().get(i).getTipoDocumento());


                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i))
                            .extracting(ProfissionalDbEntity::getDataNascimento)
                            .isEqualTo(estabelecimentoDb.getProfissional().get(i).getDataNascimento());


                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i))
                            .extracting(ProfissionalDbEntity::getNumeroDocumento)
                            .isEqualTo(estabelecimentoDb.getProfissional().get(i).getNumeroDocumento());

                    Optional.ofNullable(estabelecimentoDbSalvo.getProfissional().get(i).getAgenda()).ifPresent(agendaDb ->
                    assertThat(IntStream.range(0, agendaDb.getAgendamento().size()).boxed().collect(
                            toMap(
                                    idxl -> idxl,
                                    idxl -> agendaDb.getAgendamento().get(idxl
                                    ))))
                            .allSatisfy((l, idxl)-> {
                                assertThat(agendaDb.getAgendamento().get(l))
                                        .extracting(AgendamentoDb::getDataHora)
                                        .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                .getAgenda().getAgendamento().get(l).getDataHora());

                                assertThat(agendaDb.getAgendamento().get(l))
                                        .extracting(AgendamentoDb::getDuracao)
                                        .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                .getAgenda().getAgendamento().get(l).getDuracao());

                                assertThat(agendaDb.getAgendamento().get(l))
                                        .extracting(AgendamentoDb::getServico)
                                        .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                .getAgenda().getAgendamento().get(l).getServico());

                                assertThat(agendaDb.getAgendamento().get(l).getServico())
                                        .extracting(ServicoDbEntity::getCliente)
                                        .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                .getAgenda().getAgendamento().get(l).getServico().getCliente());
                                assertThat(agendaDb.getAgendamento().get(l).getServico())
                                        .extracting(ServicoDbEntity::getEspecialidade)
                                        .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                .getAgenda().getAgendamento().get(l).getServico().getEspecialidade());
                                assertThat(agendaDb.getAgendamento().get(l).getServico())
                                        .extracting(ServicoDbEntity::getEstabelecimento)
                                        .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                .getAgenda().getAgendamento().get(l).getServico().getEstabelecimento());
                                assertThat(agendaDb.getAgendamento().get(l).getServico())
                                        .extracting(ServicoDbEntity::getProfissional)
                                        .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                .getAgenda().getAgendamento().get(l).getServico().getProfissional());


                            }));

                assertThat(estabelecimentoDbSalvo.getProfissional().get(i))
                            .extracting(ProfissionalDbEntity::getEspecialidade)
                            .isEqualTo(estabelecimentoDb.getProfissional().get(i).getEspecialidade());
                });
        assertThat(IntStream.range(0,estabelecimentoDbSalvo.getFuncionamento().size()).boxed().collect(
                toMap(
                        idx -> idx,
                        idx -> estabelecimentoDbSalvo.getFuncionamento().get(idx
                        ))))
                .allSatisfy((i, idx)->{
                    assertThat(estabelecimentoDbSalvo.getFuncionamento().get(i))
                            .extracting(FuncionamentoDb::getDiaSemana)
                            .isEqualTo(estabelecimentoDb.getFuncionamento().get(i).getDiaSemana());

                    assertThat(IntStream.range(0,estabelecimentoDbSalvo.getFuncionamento().get(i).getPeriodoFuncionamento().size()).boxed().collect(
                            toMap(
                                    idxl -> idxl,
                                    idxl -> estabelecimentoDbSalvo.getFuncionamento().get(idxl
                                    ))))
                            .allSatisfy((l, idxl)-> {

                                assertThat(estabelecimentoDbSalvo.getFuncionamento().get(i)
                                        .getPeriodoFuncionamento().get(l))
                                        .extracting(PeriodoFuncionamentoDb::getHoraInicial)
                                        .isEqualTo(estabelecimentoDb.getFuncionamento().get(i)
                                                .getPeriodoFuncionamento().get(l).getHoraInicial());

                                assertThat(estabelecimentoDbSalvo.getFuncionamento().get(i)
                                        .getPeriodoFuncionamento().get(l))
                                        .extracting(PeriodoFuncionamentoDb::getHoraFinal)
                                        .isEqualTo(estabelecimentoDb.getFuncionamento().get(i)
                                                .getPeriodoFuncionamento().get(l).getHoraFinal());
                            });
                });
        
    }

    @Test
    void deveBuscarEstabelecimentoPorId() {

        EstabelecimentoDbEntity estabelecimentoDbSalvo = this.registrarEstabelecimento();
        UUID id = estabelecimentoDbSalvo.getId();

        Optional<EstabelecimentoDbEntity> estabelecimentoDbBuscado =
                this.estabelecimentoDbRepository.findById(id);

        estabelecimentoDbBuscado.ifPresent(estabelecimentoDb -> {
            assertThat(estabelecimentoDb)
                    .extracting(EstabelecimentoDbEntity::getNome)
                    .isEqualTo(estabelecimentoDbSalvo.getNome());
            assertThat(estabelecimentoDb.getEndereco())
                    .extracting(EnderecoDb::getRua)
                    .isEqualTo(estabelecimentoDbSalvo.getEndereco().getRua());
            assertThat(estabelecimentoDb.getEndereco())
                    .extracting(EnderecoDb::getNumero)
                    .isEqualTo(estabelecimentoDbSalvo.getEndereco().getNumero());
            assertThat(estabelecimentoDb.getEndereco())
                    .extracting(EnderecoDb::getBairro)
                    .isEqualTo(estabelecimentoDbSalvo.getEndereco().getBairro());
            assertThat(estabelecimentoDb.getEndereco())
                    .extracting(EnderecoDb::getCidade)
                    .isEqualTo(estabelecimentoDbSalvo.getEndereco().getCidade());
            assertThat(estabelecimentoDb.getEndereco())
                    .extracting(EnderecoDb::getEstado)
                    .isEqualTo(estabelecimentoDbSalvo.getEndereco().getEstado());
            assertThat(estabelecimentoDb.getEndereco())
                    .extracting(EnderecoDb::getCep)
                    .isEqualTo(estabelecimentoDbSalvo.getEndereco().getCep());
            assertThat(IntStream.range(0, estabelecimentoDb.getFoto().size()).boxed().collect(
                    toMap(
                            idx -> idx,
                            idx -> estabelecimentoDb.getFoto().get(idx
                            ))))
                    .allSatisfy((i, idx) -> {
                        assertThat(estabelecimentoDb.getFoto().get(i))
                                .isEqualTo(estabelecimentoDbSalvo.getFoto().get(i));
                    });
            assertThat(IntStream.range(0, estabelecimentoDb.getProfissional().size()).boxed().collect(
                    toMap(
                            idx -> idx,
                            idx -> estabelecimentoDb.getProfissional().get(idx
                            ))))
                    .allSatisfy((i, idx) -> {
                        assertThat(estabelecimentoDb.getProfissional().get(i))
                                .extracting(ProfissionalDbEntity::getNome)
                                .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i).getNome());

                        assertThat(estabelecimentoDb.getProfissional().get(i))
                                .extracting(ProfissionalDbEntity::getEmail)
                                .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i).getEmail());

                        assertThat(estabelecimentoDb.getProfissional().get(i))
                                .extracting(ProfissionalDbEntity::getTipoDocumento)
                                .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i).getTipoDocumento());


                        assertThat(estabelecimentoDb.getProfissional().get(i))
                                .extracting(ProfissionalDbEntity::getDataNascimento)
                                .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i).getDataNascimento());


                        assertThat(estabelecimentoDb.getProfissional().get(i))
                                .extracting(ProfissionalDbEntity::getNumeroDocumento)
                                .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i).getNumeroDocumento());

                        Optional.ofNullable(estabelecimentoDb.getProfissional().get(i).getAgenda()).ifPresent(agendaDb ->
                        assertThat(IntStream.range(0, agendaDb.getAgendamento().size()).boxed().collect(
                                toMap(
                                        idxl -> idxl,
                                        idxl -> agendaDb.getAgendamento().get(idxl
                                        ))))
                                .allSatisfy((l, idxl) -> {
                                    assertThat(agendaDb.getAgendamento().get(l))
                                            .extracting(AgendamentoDb::getDataHora)
                                            .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i)
                                                    .getAgenda().getAgendamento().get(l).getDataHora());

                                    assertThat(agendaDb.getAgendamento().get(l))
                                            .extracting(AgendamentoDb::getDuracao)
                                            .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i)
                                                    .getAgenda().getAgendamento().get(l).getDuracao());

                                    assertThat(agendaDb.getAgendamento().get(l))
                                            .extracting(AgendamentoDb::getServico)
                                            .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i)
                                                    .getAgenda().getAgendamento().get(l).getServico());

                                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico())
                                            .extracting(ServicoDbEntity::getCliente)
                                            .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                    .getAgenda().getAgendamento().get(l).getServico().getCliente());
                                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico())
                                            .extracting(ServicoDbEntity::getEspecialidade)
                                            .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                    .getAgenda().getAgendamento().get(l).getServico().getEspecialidade());
                                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico())
                                            .extracting(ServicoDbEntity::getEstabelecimento)
                                            .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                    .getAgenda().getAgendamento().get(l).getServico().getEstabelecimento());
                                    assertThat(estabelecimentoDbSalvo.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico())
                                            .extracting(ServicoDbEntity::getProfissional)
                                            .isEqualTo(estabelecimentoDb.getProfissional().get(i)
                                                    .getAgenda().getAgendamento().get(l).getServico().getProfissional());


                                }));

                        assertThat(estabelecimentoDb.getProfissional().get(i))
                                .extracting(ProfissionalDbEntity::getEspecialidade)
                                .isEqualTo(estabelecimentoDbSalvo.getProfissional().get(i).getEspecialidade());
                    });
            assertThat(IntStream.range(0, estabelecimentoDb.getFuncionamento().size()).boxed().collect(
                    toMap(
                            idx -> idx,
                            idx -> estabelecimentoDb.getFuncionamento().get(idx
                            ))))
                    .allSatisfy((i, idx) -> {
                        assertThat(estabelecimentoDb.getFuncionamento().get(i))
                                .extracting(FuncionamentoDb::getDiaSemana)
                                .isEqualTo(estabelecimentoDbSalvo.getFuncionamento().get(i).getDiaSemana());

                        assertThat(IntStream.range(0, estabelecimentoDb.getFuncionamento().get(i).getPeriodoFuncionamento().size()).boxed().collect(
                                toMap(
                                        idxl -> idxl,
                                        idxl -> estabelecimentoDb.getFuncionamento().get(idxl
                                        ))))
                                .allSatisfy((l, idxl) -> {

                                    assertThat(estabelecimentoDb.getFuncionamento().get(i)
                                            .getPeriodoFuncionamento().get(l))
                                            .extracting(PeriodoFuncionamentoDb::getHoraInicial)
                                            .isEqualTo(estabelecimentoDbSalvo.getFuncionamento().get(i)
                                                    .getPeriodoFuncionamento().get(l).getHoraInicial());

                                    assertThat(estabelecimentoDb.getFuncionamento().get(i)
                                            .getPeriodoFuncionamento().get(l))
                                            .extracting(PeriodoFuncionamentoDb::getHoraFinal)
                                            .isEqualTo(estabelecimentoDbSalvo.getFuncionamento().get(i)
                                                    .getPeriodoFuncionamento().get(l).getHoraFinal());
                                });
                    });
        });
    }

    void deveBuscarEstabelecimentoPorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    @Test
    void deveDeletarEstabelecimento() {
        EstabelecimentoDbEntity estabelecimentoDbSalvo = this.registrarEstabelecimento();
        UUID id = estabelecimentoDbSalvo.getId();

        this.estabelecimentoDbRepository.deleteById(id);
        Optional<EstabelecimentoDbEntity> estabelecimentoBuscado = this.estabelecimentoDbRepository.findById(id);

        assertThat(estabelecimentoBuscado)
                .isEmpty();
    }

    private EstabelecimentoDbEntity registrarEstabelecimento() {
        UUID id = UUID.randomUUID();

        EstabelecimentoDbEntity estabelecimentoDb = this.estabelecimentoMapper.toEstabelecimentoDbEntity(
                EstabelecimentoHelper.getEstabelecimento()
        );
        estabelecimentoDb.setId(id);

        return this.estabelecimentoDbRepository.save(estabelecimentoDb);

    }
}
