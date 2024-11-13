package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento.RealizarAgendamentoDto;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.*;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.listeners.MongoListenersConfiguration;
import br.com.fiap.postech.gestaoservicos.utils.entity.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgendamentoControllerIT {

    @Autowired
    private CadastrarClienteUseCase cadastrarClienteUseCase;
    @Autowired
    private CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase;
    @Autowired
    private CadastrarPerfilProfissionalUseCase cadastrarPerfilProfissionalUseCase;
    @Autowired
    private CadastrarEstabelecimentoUseCase cadastrarEstabelecimentoUseCase;

    @Autowired
    private MongoListenersConfiguration mongoListenersConfiguration;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
    }

    @Test
    public void deveRealizarAgendamento() throws Exception {
        Especialidade especialidade = this.registrarEspecialidade();
        ProfissionalEntity profissional = this.registrarProfissional(especialidade);
        EstabelecimentoEntity estabelecimento = this.registrarEstabelecimento(profissional);
        ClienteEntity cliente = this.registrarCliente();

        RealizarAgendamentoDto realizarAgendamentoDto =
                new RealizarAgendamentoDto(
                        cliente.getId(),
                        estabelecimento.getId(),
                        profissional.getId(),
                        especialidade.getId(),
                        LocalDateTime.now()
                );

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(realizarAgendamentoDto)
                .when()
                .post("/agendamento")
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    private ClienteEntity registrarCliente() {
        ClienteEntity cliente = ClienteHelper.getCliente();

        return this.cadastrarClienteUseCase.executa(
                cliente.getPessoa().getNome(),
                cliente.getPessoa().getEmail(),
                cliente.getPessoa().getDocumento().getNumeroDocumento(),
                cliente.getPessoa().getDocumento().getTipoDocumento(),
                cliente.getPessoa().getDataNascimento());
    }

    private Especialidade registrarEspecialidade() {
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();
        especialidade.setId(UUID.randomUUID());

        return this.cadastrarEspecialidadeUseCase.execute(especialidade);
    }

    private ProfissionalEntity registrarProfissional(Especialidade especialidade) {
        ProfissionalEntity profissionalEntity = ProfissionalHelper.getProfissional();

        return this.cadastrarPerfilProfissionalUseCase.executar(
                profissionalEntity.getPessoa().getNome(),
                profissionalEntity.getPessoa().getEmail(),
                profissionalEntity.getPessoa().getDocumento().getNumeroDocumento(),
                profissionalEntity.getPessoa().getDocumento().getTipoDocumento(),
                profissionalEntity.getPessoa().getDataNascimento()
                ,List.of(especialidade.getId()));
    }

    private EstabelecimentoEntity registrarEstabelecimento(ProfissionalEntity profissional) {
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        estabelecimento.setProfissional(List.of(profissional));
        estabelecimento.setFuncionamento(FuncionamentoHelper.getFuncionamentoList());

        return this.cadastrarEstabelecimentoUseCase.execute(estabelecimento);
    }
}
