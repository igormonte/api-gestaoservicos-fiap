package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.cliente.RequestCadastrarClienteDto;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarClienteUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.ClienteHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.fiap.postech.gestaoservicos.utils.mapper.JsonHelper.asJsonString;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
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

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestCadastrarClienteDto)
                .when()
                .post("/cliente")
                .then()
                .statusCode(HttpStatus.OK.value());
//                .body("$", hasKey("id"))
//                .body("$", hasKey("usuario"))
//                .body("$", hasKey("conteudo"))
//                .body("$", hasKey("dataCriacao"))
//                .body("$", hasKey("gostei"))
//                .body("usuario", equalTo(mensagemRequest.getUsuario()))
//                .body("conteudo", equalTo(mensagemRequest.getConteudo()));
    }

}
