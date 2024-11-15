package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.CadastrarEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Endereco;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.AdicionarProfissionalEstabelecimentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarEstabelecimentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarEstabelecimentoUseCase;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.EstabelecimentoMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EstabelecimentoHelper;
import com.callibrity.logging.test.LogTracker;
import com.callibrity.logging.test.LogTrackerStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.postech.gestaoservicos.utils.mapper.JsonHelper.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EstabelecimentoControllerTest {

    @Autowired
    private EstabelecimentoMapper estabelecimentoMapper;
    @Mock
    private CadastrarEstabelecimentoUseCase cadastrarEstabelecimentoUseCase;
    @Mock
    private BuscarEstabelecimentoUseCase buscarEstabelecimentoUseCase;
    private AdicionarProfissionalEstabelecimentoUseCase adicionarProfissionalEstabelecimentoUseCase;
    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @RegisterExtension
    LogTrackerStub logTracker = LogTrackerStub.create().recordForLevel(LogTracker.LogLevel.DEBUG)
            .recordForType(EstabelecimentoController.class);

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        EstabelecimentoController estabelecimentoController =
                new EstabelecimentoController(
                        cadastrarEstabelecimentoUseCase,
                        buscarEstabelecimentoUseCase,
                        adicionarProfissionalEstabelecimentoUseCase,
                        estabelecimentoMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(estabelecimentoController)
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
    public void deveCadastrarEstabelecimento() throws Exception {
        CadastrarEstabelecimentoDto cadastrarEstabelecimento =
                EstabelecimentoHelper.getCadastrarEstabelecimento();
        EstabelecimentoEntity estabelecimento =
                this.estabelecimentoMapper.toEstabelecimentoEntity(cadastrarEstabelecimento);

        when(cadastrarEstabelecimentoUseCase.execute(
                any(String.class),
                any(Endereco.class),
                any(List.class),
                any(List.class),
                any(List.class)))
                .thenReturn(estabelecimento);

        String content = asJsonString(cadastrarEstabelecimento);

        mockMvc.perform(post("/estabelecimento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                    .andDo(print())
                .andExpect(status().isOk());
        verify(cadastrarEstabelecimentoUseCase, times(1))
                .execute(any(String.class),
                        any(Endereco.class),
                        any(List.class),
                        any(List.class),
                        any(List.class));
    }

    @Test
    public void deveBuscarEstabelecimentoPorEspecificacao() throws Exception {
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setId(UUID.randomUUID());
        String especificacao = String.format(
                "id=eq=%s", estabelecimento.getId().toString());

        when(buscarEstabelecimentoUseCase.buscarDinamica(any(String.class)))
                .thenReturn(List.of(estabelecimento));

        mockMvc.perform(get("/estabelecimento/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("search", especificacao))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(buscarEstabelecimentoUseCase, times(1))
                .buscarDinamica(any(String.class));
    }

    @Test
    @GetMapping("/buscarPorId/{id}")
    public void getEstabelecimentoPorId() throws Exception {
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setId(UUID.randomUUID());
        String id = estabelecimento.getId().toString();

        when(buscarEstabelecimentoUseCase.buscarPorId(any(UUID.class)))
                .thenReturn(estabelecimento);

        mockMvc.perform(get("/estabelecimento/buscarPorId/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(buscarEstabelecimentoUseCase, times(1))
                .buscarPorId(any(UUID.class));
    }

}
