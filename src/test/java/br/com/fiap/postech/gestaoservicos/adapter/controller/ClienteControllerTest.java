package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.cliente.RequestCadastrarClienteDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestCadastrarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarClienteUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarClienteUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static br.com.fiap.postech.gestaoservicos.utils.mapper.JsonHelper.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClienteControllerTest {

    @Mock
    private CadastrarClienteUseCase cadastrarClienteUseCase;
    @Mock
    private BuscarClienteUseCase buscarClienteUseCase;
    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ClienteController clienteController = new ClienteController(cadastrarClienteUseCase, buscarClienteUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
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
    public void deveCadastrarCliente() throws Exception {
        ClienteEntity cliente = ClienteHelper.getCliente();


        RequestCadastrarClienteDto requestCadastrarClienteDto =
            new RequestCadastrarClienteDto(
                cliente.getPessoa().getNome(),
                cliente.getPessoa().getEmail(),
                cliente.getPessoa().getDocumento().getNumeroDocumento(),
                cliente.getPessoa().getDocumento().getTipoDocumento(),
                cliente.getPessoa().getDataNascimento());

        when(cadastrarClienteUseCase.executa(
                any(String.class),
                any(String.class),
                any(String.class),
                any(TipoDocumento.class),
                any(LocalDate.class))).thenReturn(cliente);

        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestCadastrarClienteDto)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(cadastrarClienteUseCase, times(1))
                .executa(any(String.class),
                        any(String.class),
                        any(String.class),
                        any(TipoDocumento.class),
                        any(LocalDate.class));
    }

}
