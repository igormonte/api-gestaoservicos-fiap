package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agenda;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto.IcalCalendarioDto;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto.IcalEventoDto;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto.IcalParticipanteDto;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.repository.IcalRepository;

import java.util.List;

public class AgendamentoFileGateway {

    private final IcalRepository icalRepository;

    public AgendamentoFileGateway(IcalRepository icalRepository) {
        this.icalRepository = icalRepository;
    }

    public byte[] gerarCalendario(Agenda agenda) {

        List<IcalEventoDto> evento = agenda.getAgendamento().stream().map(
            e -> {
                String titulo = String.format(
                        "%s | %s - %s",
                        e.getServico().getEstabelecimento().getNome(),
                        e.getServico().getEspecialidade().getNome(),
                        e.getDataHora().toString()
                );

                return new IcalEventoDto(
                    titulo,
                    e.getServico().getEspecialidade().getDescricao(),
                    List.of(
                        new IcalParticipanteDto(
                                e.getServico().getCliente().getPessoa().getNome(),
                                e.getServico().getCliente().getPessoa().getEmail()
                        ),
                        new IcalParticipanteDto(
                                e.getServico().getProfissional().getPessoa().getNome(),
                                e.getServico().getProfissional().getPessoa().getEmail()
                        )
                    ),
                    e.getDataHora(),
                    e.getDataHora().plusHours(1L)

                );
            }
        ).toList();

        return this.icalRepository.gerarCalendario(new IcalCalendarioDto(evento));
    }

}
