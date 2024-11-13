package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.AssociarEspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.usecase.AssociarEspecialidadeProfissionalUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarEspecialidadeUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.EspecialidadeHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static br.com.fiap.postech.gestaoservicos.utils.mapper.JsonHelper.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EspecialidadeControllerTest {

    @Mock
    private CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase;
    @Mock
    private AssociarEspecialidadeProfissionalUseCase associarEspecialidadeProfissionalUseCase;
    private MockMvc mockMvc;
    private AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        EspecialidadeController especialidadeController =
                new EspecialidadeController(
                        cadastrarEspecialidadeUseCase,
                        associarEspecialidadeProfissionalUseCase
                );
        mockMvc = MockMvcBuilders.standaloneSetup(especialidadeController)
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
    public void deveCadastrarEspecialidade() throws Exception {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();

        when(cadastrarEspecialidadeUseCase.execute(any(Especialidade.class)))
                .thenReturn(especialidade);

        mockMvc.perform(post("/especialidade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(especialidade)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(cadastrarEspecialidadeUseCase, times(1))
                .execute(any(Especialidade.class));
    }

    @Test
    public void deveAssociarEspecialidade() throws Exception {
        AssociarEspecialidadeDto associarEspecialidadeDto =
                new AssociarEspecialidadeDto(UUID.randomUUID(), UUID.randomUUID());

        when(associarEspecialidadeProfissionalUseCase.executar(any(UUID.class),any(UUID.class)))
                .thenReturn(true);

        mockMvc.perform(post("/especialidade/associar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(associarEspecialidadeDto)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(associarEspecialidadeProfissionalUseCase, times(1))
                .executar(any(UUID.class),any(UUID.class));
    }

}
