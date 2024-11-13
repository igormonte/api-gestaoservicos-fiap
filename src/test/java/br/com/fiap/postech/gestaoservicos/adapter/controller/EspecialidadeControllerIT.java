package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.AssociarEspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.AssociarEspecialidadeProfissionalUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarEspecialidadeUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarPerfilProfissionalUseCase;
import br.com.fiap.postech.gestaoservicos.utils.entity.EspecialidadeHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.postech.gestaoservicos.utils.mapper.JsonHelper.asJsonString;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EspecialidadeControllerIT {

    @LocalServerPort
    private int port;
    @Autowired
    private CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase;
    @Autowired
    private CadastrarPerfilProfissionalUseCase cadastrarPerfilProfissionalUseCase;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
    }

    @Test
    public void deveCadastrarEspecialidade() throws Exception {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        especialidade.setId(UUID.randomUUID());

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(especialidade)
                .when()
                .post("/especialidade")
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
    public void deveAssociarEspecialidade() throws Exception {
        Especialidade especialidade = this.registrarEspecialidade();
        ProfissionalEntity profissional = this.registrarProfissional();
        AssociarEspecialidadeDto associarEspecialidadeDto =
                new AssociarEspecialidadeDto(especialidade.getId(),profissional.getId());

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(associarEspecialidadeDto)
                .when()
                .post("/especialidade/associar")
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

    private ProfissionalEntity registrarProfissional() {
        ProfissionalEntity profissionalEntity = ProfissionalHelper.getProfissional();

        return this.cadastrarPerfilProfissionalUseCase.executar(

                profissionalEntity.getPessoa().getNome(),
                profissionalEntity.getPessoa().getEmail(),
                profissionalEntity.getPessoa().getDocumento().getNumeroDocumento(),
                profissionalEntity.getPessoa().getDocumento().getTipoDocumento(),
                profissionalEntity.getPessoa().getDataNascimento(),
                List.of());
    }

    private Especialidade registrarEspecialidade() {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        especialidade.setId(UUID.randomUUID());

        return this.cadastrarEspecialidadeUseCase.execute(especialidade);
    }

}
