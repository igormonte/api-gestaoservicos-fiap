package br.com.fiap.postech.gestaoservicos.core.domain.profissional;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Data
public class Agenda {
    private List<Agendamento> agendamento;

    public Agenda() {
    }

    public Agenda(List<Agendamento> agendamento) {
        this.agendamento = agendamento;
    }

    public boolean isDisponivel(LocalTime localTime) {
        Optional<Agendamento> agendamentoOptional = this.getAgendamento().stream().filter(
            agendamento -> {
                Especialidade especialidade =
                        agendamento.getServico().getEspecialidade();

                return agendamento.getDataHora().toLocalTime().isAfter(localTime) &&
                        agendamento.getDataHora().toLocalTime().isBefore(
                                localTime.plusHours(especialidade.getDuracao().getHour())
                                        .plusMinutes(especialidade.getDuracao().getMinute())
                                        .plusSeconds(especialidade.getDuracao().getSecond())
                                        .plusNanos(especialidade.getDuracao().getNano()));
        }).findAny();
        return agendamentoOptional.isEmpty();
    }

}
