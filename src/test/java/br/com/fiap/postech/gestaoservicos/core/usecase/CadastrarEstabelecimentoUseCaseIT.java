package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.adapter.gateway.EstabelecimentoDbGateway;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Endereco;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Foto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.PeriodoFuncionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.Documento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EstabelecimentoDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.EstabelecimentoDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.EstabelecimentoMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EstabelecimentoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastrarEstabelecimentoUseCaseIT {

    @Autowired
    private CadastrarEstabelecimentoUseCase cadastrarEstabelecimentoUseCase;

    @Test
    void deveExecutar() {

        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setId(UUID.randomUUID());

        EstabelecimentoEntity estabelecimentoSalvo =
                this.cadastrarEstabelecimentoUseCase.execute(
                        estabelecimento.getNome(),
                        estabelecimento.getEndereco(),
                        null,
                        estabelecimento.getFuncionamento(),
                        estabelecimento.getFoto().stream().map(Foto::getUrl).toList()
                );

        assertThat(estabelecimentoSalvo)
                .isInstanceOf(EstabelecimentoEntity.class)
                .isNotNull();
        assertThat(estabelecimentoSalvo)
                .extracting(EstabelecimentoEntity::getId)
                .isNotNull();
        assertThat(estabelecimentoSalvo)
                .extracting(EstabelecimentoEntity::getNome)
                .isEqualTo(estabelecimento.getNome());
        assertThat(estabelecimentoSalvo.getEndereco())
                .extracting(Endereco::getRua)
                .isEqualTo(estabelecimento.getEndereco().getRua());
        assertThat(estabelecimentoSalvo.getEndereco())
                .extracting(Endereco::getNumero)
                .isEqualTo(estabelecimento.getEndereco().getNumero());
        assertThat(estabelecimentoSalvo.getEndereco())
                .extracting(Endereco::getBairro)
                .isEqualTo(estabelecimento.getEndereco().getBairro());
        assertThat(estabelecimentoSalvo.getEndereco())
                .extracting(Endereco::getCidade)
                .isEqualTo(estabelecimento.getEndereco().getCidade());
        assertThat(estabelecimentoSalvo.getEndereco())
                .extracting(Endereco::getEstado)
                .isEqualTo(estabelecimento.getEndereco().getEstado());
        assertThat(estabelecimentoSalvo.getEndereco())
                .extracting(Endereco::getCep)
                .isEqualTo(estabelecimento.getEndereco().getCep());
        assertThat(IntStream.range(0,estabelecimentoSalvo.getFoto().size()).boxed().collect(
            toMap(
                idx -> idx,
                idx -> estabelecimentoSalvo.getFoto().get(idx
        ))))
        .allSatisfy((i, idx)->{
            assertThat(estabelecimentoSalvo.getFoto().get(i))
                    .extracting(Foto::getUrl)
                    .isEqualTo(estabelecimento.getFoto().get(i).getUrl());
        });

        Optional.ofNullable(estabelecimentoSalvo.getProfissional()).ifPresent(profissional ->
        assertThat(IntStream.range(0,profissional.size()).boxed().collect(
            toMap(
                idx -> idx,
                idx -> profissional.get(idx)
        )))
        .allSatisfy((i, idx)->{
            assertThat(estabelecimentoSalvo.getProfissional().get(i))
                    .extracting(ProfissionalEntity::getPessoa)
                    .isEqualTo(estabelecimento.getProfissional().get(i).getPessoa());

            assertThat(estabelecimentoSalvo.getProfissional().get(i).getPessoa())
                    .extracting(Pessoa::getNome)
                    .isEqualTo(estabelecimento.getProfissional().get(i).getPessoa().getNome());

            assertThat(estabelecimentoSalvo.getProfissional().get(i).getPessoa())
                    .extracting(Pessoa::getEmail)
                    .isEqualTo(estabelecimento.getProfissional().get(i).getPessoa().getEmail());

            assertThat(estabelecimentoSalvo.getProfissional().get(i).getPessoa())
                    .extracting(Pessoa::getDataNascimento)
                    .isEqualTo(estabelecimento.getProfissional().get(i).getPessoa().getDataNascimento());

            assertThat(estabelecimentoSalvo.getProfissional().get(i).getPessoa())
                    .extracting(Pessoa::getDocumento)
                    .isEqualTo(estabelecimento.getProfissional().get(i).getPessoa().getDocumento());

            assertThat(estabelecimentoSalvo.getProfissional().get(i).getPessoa().getDocumento())
                    .extracting(Documento::getNumeroDocumento)
                    .isEqualTo(estabelecimento.getProfissional()
                            .get(i).getPessoa().getDocumento().getNumeroDocumento());

            assertThat(estabelecimentoSalvo.getProfissional().get(i).getPessoa().getDocumento())
                    .extracting(Documento::getTipoDocumento)
                    .isEqualTo(estabelecimento.getProfissional()
                            .get(i).getPessoa().getDocumento().getTipoDocumento());

            assertThat(estabelecimentoSalvo.getProfissional().get(i))
                    .extracting(ProfissionalEntity::getAgenda)
                    .isEqualTo(estabelecimento.getProfissional().get(i).getAgenda());

            Optional.ofNullable(estabelecimentoSalvo.getProfissional().get(i).getAgenda()).ifPresent(agenda ->
                assertThat(IntStream.range(0, agenda.getAgendamento().size()).boxed().collect(
                        toMap(
                                idxl -> idxl,
                                idxl -> estabelecimentoSalvo.getProfissional().get(i).getAgenda().getAgendamento().get(idxl
                        ))))
                        .allSatisfy((l, idxl)-> {
                            assertThat(estabelecimentoSalvo.getProfissional().get(i)
                                    .getAgenda().getAgendamento().get(l))
                                    .extracting(Agendamento::getDataHora)
                                    .isEqualTo(estabelecimento.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getDataHora());

                            assertThat(estabelecimentoSalvo.getProfissional().get(i)
                                    .getAgenda().getAgendamento().get(l))
                                    .extracting(Agendamento::getDuracao)
                                    .isEqualTo(estabelecimento.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getDuracao());

                            assertThat(estabelecimentoSalvo.getProfissional().get(i)
                                    .getAgenda().getAgendamento().get(l))
                                    .extracting(Agendamento::getServico)
                                    .isEqualTo(estabelecimento.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico());

                            assertThat(estabelecimentoSalvo.getProfissional().get(i)
                                    .getAgenda().getAgendamento().get(l).getServico())
                                    .extracting(ServicoEntity::getCliente)
                                    .isEqualTo(estabelecimento.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico().getCliente());
                            assertThat(estabelecimentoSalvo.getProfissional().get(i)
                                    .getAgenda().getAgendamento().get(l).getServico())
                                    .extracting(ServicoEntity::getEspecialidade)
                                    .isEqualTo(estabelecimento.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico().getEspecialidade());
                            assertThat(estabelecimentoSalvo.getProfissional().get(i)
                                    .getAgenda().getAgendamento().get(l).getServico())
                                    .extracting(ServicoEntity::getEstabelecimento)
                                    .isEqualTo(estabelecimento.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico().getEstabelecimento());
                            assertThat(estabelecimentoSalvo.getProfissional().get(i)
                                    .getAgenda().getAgendamento().get(l).getServico())
                                    .extracting(ServicoEntity::getProfissional)
                                    .isEqualTo(estabelecimento.getProfissional().get(i)
                                            .getAgenda().getAgendamento().get(l).getServico().getProfissional());


                        }));

            assertThat(estabelecimentoSalvo.getProfissional().get(i))
                    .extracting(ProfissionalEntity::getEspecialidade)
                    .isEqualTo(estabelecimento.getProfissional().get(i).getEspecialidade());
        }));
        assertThat(IntStream.range(0,estabelecimentoSalvo.getFuncionamento().size()).boxed().collect(
            toMap(
                idx -> idx,
                idx -> estabelecimentoSalvo.getFuncionamento().get(idx
        ))))
        .allSatisfy((i, idx)->{
            assertThat(estabelecimentoSalvo.getFuncionamento().get(i))
                    .extracting(Funcionamento::getDiaSemana)
                    .isEqualTo(estabelecimento.getFuncionamento().get(i).getDiaSemana());

            assertThat(IntStream.range(0,estabelecimentoSalvo.getFuncionamento().get(i).getPeriodoFuncionamento().size()).boxed().collect(
                toMap(
                    idxl -> idxl,
                    idxl -> estabelecimentoSalvo.getFuncionamento().get(idxl
            ))))
            .allSatisfy((l, idxl)-> {

                assertThat(estabelecimentoSalvo.getFuncionamento().get(i)
                        .getPeriodoFuncionamento().get(l))
                        .extracting(PeriodoFuncionamento::getHoraInicial)
                        .isEqualTo(estabelecimento.getFuncionamento().get(i)
                                .getPeriodoFuncionamento().get(l).getHoraInicial());

                assertThat(estabelecimentoSalvo.getFuncionamento().get(i)
                        .getPeriodoFuncionamento().get(l))
                        .extracting(PeriodoFuncionamento::getHoraFinal)
                        .isEqualTo(estabelecimento.getFuncionamento().get(i)
                                .getPeriodoFuncionamento().get(l).getHoraFinal());
            });
        });


    }

}
