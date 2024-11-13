package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.CadastrarEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarEstabelecimentoUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.EstabelecimentoHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstabelecimentoControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CadastrarEstabelecimentoUseCase cadastrarEstabelecimentoUseCase;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
    }

    @Test
    public void deveCadastrarEstabelecimento() throws Exception {
        CadastrarEstabelecimentoDto cadastrarEstabelecimento =
                EstabelecimentoHelper.getCadastrarEstabelecimento();

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(cadastrarEstabelecimento)
                .when()
                .post("/estabelecimento")
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

    @Test
    public void deveBuscarEstabelecimentoPorEspecificacao() throws Exception {
        EstabelecimentoEntity estabelecimento =
                this.registrarEstabelecimento();
        String especificacao = String.format(
                "id=eq=%s", estabelecimento.getId().toString());

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estabelecimento/buscar?search=" + especificacao)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @GetMapping("/buscarPorId/{id}")
    public void getEstabelecimentoPorId() throws Exception {
        EstabelecimentoEntity estabelecimento =
                this.registrarEstabelecimento();
        String id = estabelecimento.getId().toString();


        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estabelecimento/buscarPorId/" + id)
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    private EstabelecimentoEntity registrarEstabelecimento() {
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setId(UUID.randomUUID());

        return this.cadastrarEstabelecimentoUseCase.execute(estabelecimento);
    }

}
