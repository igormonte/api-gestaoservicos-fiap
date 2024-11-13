package br.com.fiap.postech.gestaoservicos.performance;


import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ApiPerformanceSimulation extends Simulation {


    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    ActionBuilder cadastrarClienteRequest = http("cadastrar cliente")
            .post("/cliente")
            .body(StringBody("{ \"nome\": \"José Roberto da Silva\", \"email\": \"jose.silva@exemplo.com\", \"numeroDocumento\": \"12312312312\", \"tipoDocumento\": \"CPF\", \"dataNascimento\": \"2000-05-23\" }"))
            .check(status().is(200))
            .check(jsonPath("$.id").saveAs("idCliente"));

    ActionBuilder cadastrarProfissionalRequest = http("cadastrar profissional")
            .post("/profissional")
            .body(StringBody("{ \"nome\": \"José Roberto da Silva\", \"email\": \"jose.silva@exemplo.com\", \"numeroDocumento\": \"12312312312\", \"tipoDocumento\": \"CPF\", \"dataNascimento\": \"2000-05-23\" }"))
            .check(status().is(200))
            .check(jsonPath("$.id").saveAs("idProfissional"));

    ActionBuilder cadastrarEspecialidadeRequest = http("cadastrar especialidade")
            .post("/especialidade")
            .body(StringBody("{ \"nome\": \"Corte de Cabelo\", \"descricao\": \"\" }"))
            .check(status().is(200))
            .check(jsonPath("$.id").saveAs("idEspecialidade"));

    ActionBuilder cadastrarEstabelecimentoRequest = http("cadastrar estabelecimento")
            .post("/estabelecimento")
            .body(StringBody("{\"nome\": \"Salão de Beleza Dona Lurdez\",\"endereco\": { \"rua\": \"Rua exemplo\", \"numero\": 1, \"bairro\": \"Bairro Exemplo\", \"cidade\": \"Cidade Exemplo\", \"estado\": \"SP\", \"cep\": \"000000-000\"}, \"funcionamento\": [ { \"diaSemana\": \"SEGUNDA\", \"periodoFuncionamento\": [{ \"horaInicial\": \"00:00\", \"horaFinal\": \"23:59\" }] }, { \"diaSemana\": \"TERCA\", \"periodoFuncionamento\": [{ \"horaInicial\": \"00:00\", \"horaFinal\": \"23:59\" }] }, { \"diaSemana\": \"QUARTA\", \"periodoFuncionamento\": [{ \"horaInicial\": \"00:00\", \"horaFinal\": \"23:59\" }] }, { \"diaSemana\": \"QUINTA\", \"periodoFuncionamento\": [{ \"horaInicial\": \"00:00\", \"horaFinal\": \"23:59\" }] }, { \"diaSemana\": \"SEXTA\", \"periodoFuncionamento\": [{ \"horaInicial\": \"00:00\", \"horaFinal\": \"23:59\" }] }, { \"diaSemana\": \"SABADO\", \"periodoFuncionamento\": [{ \"horaInicial\": \"00:00\", \"horaFinal\": \"23:59\" }] }, { \"diaSemana\": \"DOMINGO\", \"periodoFuncionamento\": [{ \"horaInicial\": \"00:00\", \"horaFinal\": \"23:59\" }] } ], \"foto\": [  \"https://example.com/foto.jpg\" ]}"))
            .check(status().is(200))
            .check(jsonPath("$.id").saveAs("idEstabelecimento"));

    ActionBuilder associarEspecilidadeRequest = http("associar especialidade")
            .post("/especialidade/associar")
            .body(StringBody("{ \"idEspecialidade\": \"#{idEspecialidade}\", \"idProfissional\": \"#{idProfissional}\" }"))
            .check(status().is(200));

    ActionBuilder adicionarProfissionalEstabelecimentoRequest = http("adicionar profissional estabelecimento")
            .post("/estabelecimento/profissional/adicionar")
            .body(StringBody("{ \"idEstabelecimento\": \"#{idEstabelecimento}\", \"idProfissional\": \"#{idProfissional}\" }"))
            .check(status().is(200));

    ActionBuilder realizarAgendamentoRequest = http("realizar agendamento")
            .post("/agendamento")
            .body(StringBody("{ \"idCliente\": \"#{idCliente}\", \"idEstabelecimento\": \"#{idEstabelecimento}\", \"idProfissional\": \"#{idProfissional}\", \"idEspecialidade\": \"#{idEspecialidade}\", \"dataHora\": \""+ LocalDateTime.now().plusHours(1) +"\" }"))
            .check(status().is(200));
    ActionBuilder buscarEstabelecimentoDinamicamenteRequest = http("buscar estabelecimentos dinamicamente")
            .get("/estabelecimento/buscar")
            .queryParam("search", "id=eq=#{idEstabelecimento}")
            .check(status().is(200));

    ScenarioBuilder cenarioCadastrarCliente = scenario("Cadastrar cliente")
            .exec(cadastrarClienteRequest);

    ScenarioBuilder cenarioCadastrarProfissional = scenario("Cadastrar profissional")
            .exec(cadastrarProfissionalRequest);

    ScenarioBuilder cenarioCadastrarEspecialidade = scenario("Cadastrar especialidade")
            .exec(cadastrarEspecialidadeRequest);


    ScenarioBuilder cenarioAssociarEspecialidade = scenario("Associar especialidade")
            .exec(cadastrarEspecialidadeRequest)
            .exec(cadastrarProfissionalRequest)
            .exec(associarEspecilidadeRequest);

    ScenarioBuilder cenarioRealizarAgendamento = scenario("Realizar agendamento")
            .exec(cadastrarClienteRequest)
            .exec(cadastrarEspecialidadeRequest)
            .exec(cadastrarProfissionalRequest)
            .exec(associarEspecilidadeRequest)
            .exec(cadastrarEstabelecimentoRequest)
            .exec(adicionarProfissionalEstabelecimentoRequest)
            .exec(realizarAgendamentoRequest);

    ScenarioBuilder cenarioBuscarEstabelecimentoDinamicamente = scenario("Buscar Estabelecimento Dinamicamente")
            .exec(cadastrarEstabelecimentoRequest)
            .exec(buscarEstabelecimentoDinamicamenteRequest);


    {
        setUp(
                cenarioCadastrarCliente.injectOpen(
                        rampUsersPerSec(1).to(10).during(10),
                        constantUsersPerSec(10).during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10).to(1).during(10)),
                cenarioCadastrarProfissional.injectOpen(
                        rampUsersPerSec(1).to(4).during(10),
                        constantUsersPerSec(4).during(Duration.ofSeconds(60)),
                        rampUsersPerSec(4).to(1).during(10)),
                cenarioCadastrarEspecialidade.injectOpen(
                        rampUsersPerSec(1).to(4).during(10),
                        constantUsersPerSec(4).during(Duration.ofSeconds(60)),
                        rampUsersPerSec(4).to(1).during(10)),
                cenarioAssociarEspecialidade.injectOpen(
                        rampUsersPerSec(1).to(4).during(10),
                        constantUsersPerSec(4).during(Duration.ofSeconds(60)),
                        rampUsersPerSec(4).to(1).during(10)),
                cenarioRealizarAgendamento.injectOpen(
                        rampUsersPerSec(1).to(10).during(10),
                        constantUsersPerSec(10).during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10).to(1).during(10)),
                cenarioBuscarEstabelecimentoDinamicamente.injectOpen(
                        rampUsersPerSec(1).to(10).during(10),
                        constantUsersPerSec(10).during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10).to(1).during(10))
        ).protocols(httpProtocol)
            .assertions(
                    global().responseTime().max().lt(1000),
                    global().failedRequests().count().is(0L));;
    }
}