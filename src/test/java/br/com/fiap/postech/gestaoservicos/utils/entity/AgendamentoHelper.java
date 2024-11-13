package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agenda;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AgendamentoHelper {

    public static Agendamento getAgendamento() {
        LocalDateTime dataHora = LocalDateTime.now().plusHours(1).withSecond(0).withNano(0);
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setId(UUID.randomUUID());
        ProfissionalEntity profissional = estabelecimento.getProfissional().stream().findFirst().get();
        profissional.setId(UUID.randomUUID());
        ClienteEntity cliente = ClienteHelper.getCliente();
        cliente.setId(UUID.randomUUID());
        profissional.adicionarEspecialidade(EspecialidadeHelper.getEspecialidade());
        Especialidade especialidade = profissional.getEspecialidade().stream().findFirst().get();
        ServicoEntity servico = new ServicoEntity(estabelecimento, profissional, cliente, especialidade);
        return new Agendamento(dataHora, servico);
    }
}
