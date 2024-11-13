package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento.RealizarAgendamentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.facade.ExportaAgendamentoFacade;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscaAgendamentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.RealizarAgendamentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.RealizarAvaliacaoUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.fiap.postech.gestaoservicos.utils.mapper.JsonHelper.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AgendamentoControllerTest {

    @Mock
    private RealizarAgendamentoUseCase realizarAgendamentoUseCase;
    @Mock
    private RealizarAvaliacaoUseCase realizarAvaliacaoUseCase;
    @Mock
    private ExportaAgendamentoFacade exportaAgendamentoFacade;
    @Mock
    private BuscaAgendamentoUseCase buscaAgendamentoUseCase;
    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        AgendamentoController agendamentoController = new AgendamentoController(realizarAgendamentoUseCase,
                realizarAvaliacaoUseCase, exportaAgendamentoFacade, buscaAgendamentoUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    public void deveRealizarAgendamento() throws Exception {
        RealizarAgendamentoDto realizarAgendamentoDto =
                RealizarAgendamentoHelper.getRealizarAgendamentoDto();

        Agendamento agendamento = getAgendamento(realizarAgendamentoDto);

        when(realizarAgendamentoUseCase.executar(any(UUID.class), any(UUID.class), any(UUID.class), any(UUID.class), any(LocalDateTime.class)))
                .thenReturn(agendamento);

        mockMvc.perform(post("/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(realizarAgendamentoDto)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(realizarAgendamentoUseCase, times(1))
                .executar(any(UUID.class), any(UUID.class), any(UUID.class), any(UUID.class),any(LocalDateTime.class));

    }

    private Agendamento getAgendamento(RealizarAgendamentoDto realizarAgendamentoDto) {

        ClienteEntity cliente = ClienteHelper.getCliente();
        cliente.setId(realizarAgendamentoDto.idCliente());
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        profissional.setEspecialidade(List.of(especialidade));
        profissional.setId(realizarAgendamentoDto.idProfissional());
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setId(realizarAgendamentoDto.idEstabelecimento());
        ServicoEntity servico = new ServicoEntity(
                estabelecimento,
                profissional,
                cliente,
                profissional.getEspecialidade().get(0)
        );
        return new Agendamento(
                realizarAgendamentoDto.dataHora(),
                servico
            );
    }

}
