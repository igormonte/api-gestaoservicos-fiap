package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.RealizarAgendamentoUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.AgendamentoHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgendamentoDbGatewayIT {

    @Autowired
    private AgendamentoDbGateway agendamentoDbGateway;

    @Test
    void deveExecutar() {
        Agendamento agendamento = AgendamentoHelper.getAgendamento();

        Agendamento agendamentoSalvo =
                this.agendamentoDbGateway.criarAgendamento(agendamento);

        assertThat(agendamentoSalvo)
                .isNotNull()
                .isInstanceOf(Agendamento.class);

        assertThat(agendamentoSalvo)
                .extracting(Agendamento::getId)
                .isNotNull();

        assertThat(agendamentoSalvo)
                .isNotNull()
                .extracting(Agendamento::getDataHora)
                .isEqualTo(AgendamentoHelper.getAgendamento().getDataHora());

        assertThat(agendamentoSalvo)
                .extracting(Agendamento::getDuracao)
                .isEqualTo(agendamento.getDuracao());

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
