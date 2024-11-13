package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Fail.fail;

public class AgendamentoDbRepositoryTest {

    @Mock
    private AgendamentoDbRepository AgendamentoDbRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    void deveSalvarAgendamento() {
        fail("Teste não implementado");
    }

    void deveBuscarAgendamentoPorId() {
        fail("Teste não implementado");}

    void deveBuscarAgendamentoPorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    void deveAtualizarAgendamento() {
        fail("Teste não implementado");
    }

    void deveDeletarAgendamento() {
        fail("Teste não implementado");
    }
}
