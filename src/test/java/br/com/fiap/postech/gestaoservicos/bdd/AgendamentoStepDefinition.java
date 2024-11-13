package br.com.fiap.postech.gestaoservicos.bdd;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento.RealizarAgendamentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.cliente.RequestCadastrarClienteDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.CadastrarEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestCadastrarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestEspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento.ResponseEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.utils.entity.ClienteHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EspecialidadeHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EstabelecimentoHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AgendamentoStepDefinition {

    private ClienteEntity cliente;
    private Especialidade especialidade;
    private ProfissionalEntity profissional;
    private ResponseEstabelecimentoDto estabelecimento;
    private Response response;


    @Dado("que o agentamento tenha um cliente registrado")
    public void que_o_agentamento_tenha_um_cliente_registrado() {
        ClienteEntity cliente = ClienteHelper.getCliente();

        RequestCadastrarClienteDto requestCadastrarClienteDto =
                new RequestCadastrarClienteDto(
                        cliente.getPessoa().getNome(),
                        cliente.getPessoa().getEmail(),
                        cliente.getPessoa().getDocumento().getNumeroDocumento(),
                        cliente.getPessoa().getDocumento().getTipoDocumento(),
                        cliente.getPessoa().getDataNascimento());
        this.cliente = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestCadastrarClienteDto)
                .when().post("/cliente")
                .then().extract().as(ClienteEntity.class);
    }
    @Dado("o agentamento tenha que um especialidade esteja registrada")
    public void o_agentamento_tenha_que_um_especialidade_esteja_registrada() {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        this.especialidade = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(especialidade)
                .when().post("/especialidade")
                .then().extract().as(Especialidade.class);
    }
    @Dado("o agentamento tenha que um profissional esteja registrado")
    public void o_agentamento_tenha_que_um_profissional_esteja_registrado() {
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();

        RequestCadastrarProfissionalDto requestCadastrarProfissionalDto =
                new RequestCadastrarProfissionalDto(
                        profissional.getPessoa().getNome(),
                        profissional.getPessoa().getEmail(),
                        profissional.getPessoa().getDocumento().getNumeroDocumento(),
                        profissional.getPessoa().getDocumento().getTipoDocumento(),
                        profissional.getPessoa().getDataNascimento(),
                        List.of(new RequestEspecialidadeDto(especialidade.getId())));

        this.profissional = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestCadastrarProfissionalDto)
                .when().post("/profissional")
                .then().extract().as(ProfissionalEntity.class);
    }
    @Dado("o agentamento tenha que um estabelecimento está registrado")
    public void o_agentamento_tenha_que_um_estabelecimento_está_registrado() {
        CadastrarEstabelecimentoDto cadastrarEstabelecimentoDto =
                EstabelecimentoHelper.getCadastrarEstabelecimento(profissional);

        this.estabelecimento = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(cadastrarEstabelecimentoDto)
                .when().post("/estabelecimento")
                .then().extract().as(ResponseEstabelecimentoDto.class);
    }

    @Quando("submeter um novo agendamento")
    public void submeter_um_novo_agendamento() {
        RealizarAgendamentoDto realizarAgendamentoDto =
                new RealizarAgendamentoDto(
                        cliente.getId(),
                        estabelecimento.id(),
                        profissional.getId(),
                        especialidade.getId(),
                        LocalDateTime.now().plusDays(1)
                );
        this.response = given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(realizarAgendamentoDto)
                .when().post("/agendamento");
    }
    @Então("o agendamento é criado com sucesso")
    public void o_agendamento_é_criado_com_sucesso() {
        this.response.then()
                .statusCode(HttpStatus.OK.value());
    }

}
