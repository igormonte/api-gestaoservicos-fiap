package br.com.fiap.postech.gestaoservicos.bdd;

import br.com.fiap.postech.gestaoservicos.adapter.dto.AssociarEspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestCadastrarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.utils.entity.EspecialidadeHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EspecialidadeStepDefinition {

    public Especialidade especialidade;
    private ProfissionalEntity profissional;
    private Response response;

    @Quando("submeter uma nova especialidade")
    public Especialidade submeter_um_novo_especialidade() {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(especialidade)
                .when().post("/especialidade");
        return this.response.then().extract().as(Especialidade.class);
    }
    @Então("a especialidade é criada com sucesso")
    public void a_especialidade_é_criado_com_sucesso() {
        this.response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Dado("que uma especialidade está registrada")
    public void que_uma_especialidade_está_registrada() {
        this.especialidade = this.submeter_um_novo_especialidade();
    }

    @Dado("que um profissional está registrado")
    public void que_um_profissional_está_registrado() {
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();

        RequestCadastrarProfissionalDto requestCadastrarProfissionalDto =
                new RequestCadastrarProfissionalDto(
                        profissional.getPessoa().getNome(),
                        profissional.getPessoa().getEmail(),
                        profissional.getPessoa().getDocumento().getNumeroDocumento(),
                        profissional.getPessoa().getDocumento().getTipoDocumento(),
                        profissional.getPessoa().getDataNascimento(),
                        null);
        
        this.profissional = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestCadastrarProfissionalDto)
                .when().post("/profissional")
                .then().extract().as(ProfissionalEntity.class);
    }

    @Quando("submeter a associacao da especialidade ao profissional")
    public void submeter_a_associacao_da_especialidade_ao_profissional() {
        AssociarEspecialidadeDto associarEspecialidadeDto =
                new AssociarEspecialidadeDto(especialidade.getId(), profissional.getId());
        System.out.println(associarEspecialidadeDto);
        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(associarEspecialidadeDto)
                .when().post("/especialidade/associar");
    }

    @Então("a especialidade é associada com sucesso")
    public void a_especialidade_é_associada_com_sucesso() {
        this.response.then()
                .statusCode(HttpStatus.OK.value());
    }

}
