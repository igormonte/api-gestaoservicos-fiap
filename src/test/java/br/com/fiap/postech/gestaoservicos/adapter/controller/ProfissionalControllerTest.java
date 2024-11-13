package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestCadastrarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarPerfilProfissionalUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.EspecialidadeHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static br.com.fiap.postech.gestaoservicos.utils.mapper.JsonHelper.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfissionalControllerTest {


    @Mock
    private CadastrarPerfilProfissionalUseCase cadastrarPerfilProfissionalUseCase;
    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ProfissionalController profissionalController =
                new ProfissionalController(cadastrarPerfilProfissionalUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(profissionalController)
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
    public void deveCadastrarProfissional() throws Exception {
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();

        RequestCadastrarProfissionalDto requestCadastrarProfissionalDto =
                new RequestCadastrarProfissionalDto(
                        profissional.getPessoa().getNome(),
                        profissional.getPessoa().getEmail(),
                        profissional.getPessoa().getDocumento().getNumeroDocumento(),
                        profissional.getPessoa().getDocumento().getTipoDocumento(),
                        profissional.getPessoa().getDataNascimento(),null);


        when(cadastrarPerfilProfissionalUseCase.executar(
                any(String.class), any(String.class), any(String.class), any(TipoDocumento.class), any(LocalDate.class), any(List.class)))
                .thenReturn(profissional);

        mockMvc.perform(post("/profissional")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestCadastrarProfissionalDto)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(cadastrarPerfilProfissionalUseCase, times(1))
                .executar(any(String.class), any(String.class), any(String.class), any(TipoDocumento.class), any(LocalDate.class), any(List.class));
    }
}
