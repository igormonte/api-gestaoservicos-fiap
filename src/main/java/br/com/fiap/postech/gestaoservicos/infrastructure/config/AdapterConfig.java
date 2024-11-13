package br.com.fiap.postech.gestaoservicos.infrastructure.config;

import br.com.fiap.postech.gestaoservicos.adapter.facade.ExportaAgendamentoFacade;
import br.com.fiap.postech.gestaoservicos.adapter.facade.ExportaAgendamentoFacadeImpl;
import br.com.fiap.postech.gestaoservicos.adapter.gateway.AgendamentoFileGateway;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscaAgendamentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarClienteUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarProfissionalUseCase;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.repository.IcalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AdapterConfig {

    @Bean
    AgendamentoFileGateway agendamentoFileGateway (
            IcalRepository icalRepository
    ) {
        return new AgendamentoFileGateway(icalRepository);
    }

    @Bean
    ExportaAgendamentoFacade getExportaAgendamentoFacade(
            BuscarClienteUseCase buscarClienteUseCase,
            BuscarProfissionalUseCase buscarProfissionalUseCase,
            BuscaAgendamentoUseCase buscaAgendamentoUseCase,
            AgendamentoFileGateway agendamentoFileGateway
    ) {
        return new ExportaAgendamentoFacadeImpl(
                buscarClienteUseCase,
                buscarProfissionalUseCase,
                buscaAgendamentoUseCase,
                agendamentoFileGateway
        );
    }
}
