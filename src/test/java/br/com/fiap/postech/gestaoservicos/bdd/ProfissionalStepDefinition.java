package br.com.fiap.postech.gestaoservicos.bdd;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestCadastrarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class ProfissionalStepDefinition {

    private Response response;

    @Quando("submeter um novo profissional")
    public void submeter_um_novo_profissional() {
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();

        RequestCadastrarProfissionalDto requestCadastrarProfissionalDto =
                new RequestCadastrarProfissionalDto(
                        profissional.getPessoa().getNome(),
                        profissional.getPessoa().getEmail(),
                        profissional.getPessoa().getDocumento().getNumeroDocumento(),
                        profissional.getPessoa().getDocumento().getTipoDocumento(),
                        profissional.getPessoa().getDataNascimento(),
                        null);
        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestCadastrarProfissionalDto)
                .when().post("/profissional");
    }
    @Então("o profissional é criado com sucesso")
    public void o_profissional_é_criado_com_sucesso() {
        this.response.then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(ProfissionalEntity.class);
    }

}
