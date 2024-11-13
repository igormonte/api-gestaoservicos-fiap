package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.adapter.gateway.ClienteDbGateway;
import br.com.fiap.postech.gestaoservicos.adapter.gateway.EspecialidadeDbGateway;
import br.com.fiap.postech.gestaoservicos.adapter.gateway.EstabelecimentoDbGateway;
import br.com.fiap.postech.gestaoservicos.adapter.gateway.ProfissionalDbGateway;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.utils.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RealizarAgendamentoUseCaseIT {

    @Autowired
    private RealizarAgendamentoUseCase realizarAgendamentoUseCase;

    @Autowired
    private ProfissionalDbGateway profissionalDbGateway;

    @Autowired
    private ClienteDbGateway clienteDbGateway;
    @Autowired
    private EstabelecimentoDbGateway estabelecimentoDbGateway;
    @Autowired
    private EspecialidadeDbGateway especialidadeDbGateway;

    @Test
    void deveExecutar() {
        LocalDateTime dataHora = LocalDateTime.now().plusHours(1).withSecond(0).withNano(0);
        Especialidade especialidade = registrarEspecialidade();
        ProfissionalEntity profissional = registrarProfissional(especialidade);
        EstabelecimentoEntity estabelecimento = registrarEstabelecimento(profissional);
        ClienteEntity cliente = registrarCliente();
        estabelecimento.setProfissional(List.of(profissional));

        Agendamento agendamentoSalvo =
                this.realizarAgendamentoUseCase.executar(
                        cliente.getId(),
                        estabelecimento.getId(),
                        profissional.getId(),
                        especialidade.getId(),
                        dataHora
                );

        assertThat(agendamentoSalvo)
                .isNotNull()
                .isInstanceOf(Agendamento.class);

        assertThat(agendamentoSalvo)
                .extracting(Agendamento::getId)
                .isNotNull();

        assertThat(agendamentoSalvo)
                .isNotNull()
                .extracting(Agendamento::getDataHora)
                .isEqualTo(dataHora);

//        assertThat(agendamentoSalvo)
//                .extracting(Agendamento::getDuracao)
//                .isEqualTo(agendamento.getDuracao());

        assertThat(agendamentoSalvo.getServico().getCliente())
                .extracting(ClienteEntity::getId)
                .isEqualTo(cliente.getId());

        assertThat(agendamentoSalvo.getServico().getEspecialidade())
                .extracting(Especialidade::getId)
                .isEqualTo(especialidade.getId());

        assertThat(agendamentoSalvo.getServico().getProfissional())
                .extracting(ProfissionalEntity::getId)
                .isEqualTo(profissional.getId());

        assertThat(agendamentoSalvo.getServico().getEstabelecimento())
                .extracting(EstabelecimentoEntity::getId)
                .isEqualTo(estabelecimento.getId());

    }

    private Especialidade registrarEspecialidade() {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        especialidade.setId(UUID.randomUUID());

        return this.especialidadeDbGateway.criarEspecialidade(especialidade);
    }

    private EstabelecimentoEntity registrarEstabelecimento(ProfissionalEntity profissional) {

        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setId(UUID.randomUUID());
        estabelecimento.setProfissional(List.of(profissional));

        return this.estabelecimentoDbGateway.criarEstabelecimento(estabelecimento);
    }

    private ClienteEntity registrarCliente() {

        ClienteEntity cliente = ClienteHelper.getCliente();
        cliente.setId(UUID.randomUUID());

        return this.clienteDbGateway.salvarCliente(cliente);
    }

    private ProfissionalEntity registrarProfissional(Especialidade especialidade) {

        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        profissional.setEspecialidade(List.of(especialidade));
        profissional.setId(UUID.randomUUID());

        return this.profissionalDbGateway.salvarProfissional(profissional);
    }

}
