package br.com.fiap.postech.gestaoservicos.adapter.facade;

import br.com.fiap.postech.gestaoservicos.adapter.gateway.AgendamentoFileGateway;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agenda;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscaAgendamentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarClienteUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarProfissionalUseCase;

import java.util.List;
import java.util.UUID;

public class ExportaAgendamentoFacadeImpl implements ExportaAgendamentoFacade {

    private final BuscarClienteUseCase buscarClienteUseCase;
    private final BuscarProfissionalUseCase buscarProfissionalUseCase;
    private final BuscaAgendamentoUseCase buscaAgendamentoUseCase;
    private final AgendamentoFileGateway agendamentoFileGateway;

    public ExportaAgendamentoFacadeImpl(
            BuscarClienteUseCase buscarClienteUseCase,
            BuscarProfissionalUseCase buscarProfissionalUseCase,
            BuscaAgendamentoUseCase buscaAgendamentoUseCase,
            AgendamentoFileGateway agendamentoFileGateway) {
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.buscarProfissionalUseCase = buscarProfissionalUseCase;
        this.buscaAgendamentoUseCase = buscaAgendamentoUseCase;
        this.agendamentoFileGateway = agendamentoFileGateway;
    }

    @Override
    public byte[] exportaAgendamentoCliente(UUID idCliente) {

        ClienteEntity cliente = this.buscarClienteUseCase.porId(idCliente);

        if(cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        List<Agendamento> agendamentos = buscaAgendamentoUseCase.buscarPorClienteId(idCliente);

        return agendamentoFileGateway.gerarCalendario(new Agenda(agendamentos));
    }

    @Override
    public byte[] exportaAgendamentoProfissional(UUID idProfissional) {

        ProfissionalEntity profissional = this.buscarProfissionalUseCase.porId(idProfissional);

        if(profissional == null) {
            throw new RuntimeException("Profissional não encontrado");
        }

        List<Agendamento> agendamentos = buscaAgendamentoUseCase.buscarPorProfissionalId(idProfissional);

        return agendamentoFileGateway.gerarCalendario(new Agenda(agendamentos));
    }



}
