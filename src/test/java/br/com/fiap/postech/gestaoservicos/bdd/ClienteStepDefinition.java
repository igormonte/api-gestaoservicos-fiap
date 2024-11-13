package br.com.fiap.postech.gestaoservicos.bdd;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.cliente.RequestCadastrarClienteDto;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.utils.entity.ClienteHelper;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class ClienteStepDefinition {

    private Response response;

    @Quando("submeter um novo cliente")
    public void submeter_um_novo_cliente() {
        ClienteEntity cliente = ClienteHelper.getCliente();

        RequestCadastrarClienteDto requestCadastrarClienteDto =
                new RequestCadastrarClienteDto(
                        cliente.getPessoa().getNome(),
                        cliente.getPessoa().getEmail(),
                        cliente.getPessoa().getDocumento().getNumeroDocumento(),
                        cliente.getPessoa().getDocumento().getTipoDocumento(),
                        cliente.getPessoa().getDataNascimento());
        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestCadastrarClienteDto)
                .when().post("/cliente");
    }
    @Então("o cliente é criado com sucesso")
    public void o_cliente_é_criado_com_sucesso() {
        this.response.then()
                .statusCode(HttpStatus.OK.value());
    }
    @Então("deve ser apresentado")
    public void deve_ser_apresentado() {
        this.response.then()
                .extract().as(ClienteEntity.class);
    }
}
