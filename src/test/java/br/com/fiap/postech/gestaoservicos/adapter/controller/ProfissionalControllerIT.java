package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestCadastrarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestEspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.adapter.handler.GlobalExceptionHandler;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
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
public class ProfissionalControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
    }

    @Test
    public void deveCadastrarProfissional() throws Exception {
        Especialidade especialidade = this.registrarEspecialidade();
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        profissional.setEspecialidade(List.of(especialidade));

        RequestCadastrarProfissionalDto requestCadastrarProfissionalDto =
            new RequestCadastrarProfissionalDto(
                profissional.getPessoa().getNome(),
                profissional.getPessoa().getEmail(),
                profissional.getPessoa().getDocumento().getNumeroDocumento(),
                profissional.getPessoa().getDocumento().getTipoDocumento(),
                profissional.getPessoa().getDataNascimento(),
                profissional.getEspecialidade().stream()
                    .map(e->new RequestEspecialidadeDto(e.getId())).toList()
            );

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestCadastrarProfissionalDto)
                .when()
                .post("/profissional")
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

    private Especialidade registrarEspecialidade() {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        especialidade.setId(UUID.randomUUID());

        return this.cadastrarEspecialidadeUseCase.execute(especialidade);
    }
}
