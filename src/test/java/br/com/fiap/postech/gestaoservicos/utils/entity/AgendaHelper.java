package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agenda;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AgendaHelper {

    public static Agenda getAgenda() {

        List<Agendamento> agendamentoList = new ArrayList<>();
        agendamentoList.add(AgendamentoHelper.getAgendamento());

        return new Agenda(agendamentoList);
    }
}
