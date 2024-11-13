package br.com.fiap.postech.gestaoservicos.bdd;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.CadastrarEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento.ResponseProfissionalDto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.utils.entity.EstabelecimentoHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EstabelecimentoStepDefinition {

    private EstabelecimentoEntity estabelecimento;
    private Response response;

    @Quando("submeter um novo estabelecimento")
    public EstabelecimentoEntity submeter_um_novo_estabelecimento() {

        CadastrarEstabelecimentoDto cadastrarEstabelecimentoDto =
                EstabelecimentoHelper.getCadastrarEstabelecimento();

        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(cadastrarEstabelecimentoDto)
                .when().post("/estabelecimento");

        return this.response.then().extract().as(EstabelecimentoEntity.class, this.getMapper());
    }
    @Então("o estabelecimento é criado com sucesso")
    public void o_estabelecimento_é_criado_com_sucesso() {
        this.response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Dado("que um estabelecimento está registrado")
    public void que_um_estabelecimento_está_registrado() {
        this.estabelecimento  = this.submeter_um_novo_estabelecimento();
    }
    @Quando("ao buscar um estabelecimento")
    public void ao_buscar_um_estabelecimento() {
        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estabelecimento)
                .when().get("/estabelecimento/buscarPorId/" + this.estabelecimento.getId().toString());
    }
    @Então("o estabelecimento é apresentado")
    public void o_estabelecimento_é_apresentado() {
        this.response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("ao buscar um estabelecimentos por filtro")
    public void ao_buscar_um_estabelecimentos_por_filtro() {
        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estabelecimento)
                .when().get("/estabelecimento/buscar?search=id=eq=" + this.estabelecimento.getId().toString());
    }
    @Então("estabelecimentos são apresentados")
    public void estabelecimentos_são_apresentados() {
        this.response.then()
                .statusCode(HttpStatus.OK.value());
    }
    private Jackson2Mapper getMapper() {
        return new Jackson2Mapper((type, s) -> {
            ObjectMapper om = new ObjectMapper().findAndRegisterModules();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        });
    }

}
