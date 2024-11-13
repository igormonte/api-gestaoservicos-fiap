package br.com.fiap.postech.gestaoservicos.adapter.facade;

import br.com.fiap.postech.gestaoservicos.adapter.gateway.AgendamentoFileGateway;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agenda;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscaAgendamentoUseCase;

import java.util.List;
import java.util.UUID;

public interface ExportaAgendamentoFacade {

    public byte[] exportaAgendamentoCliente(UUID idCliente);

    public byte[] exportaAgendamentoProfissional(UUID idProfissional);



}
