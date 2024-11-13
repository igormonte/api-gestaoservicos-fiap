package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Fail.fail;

public class ClienteDbRepositoryTest {

    @Mock
    private ClienteDbRepository ClienteDbRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    void deveSalvarCliente() {
        fail("Teste não implementado");
    }

    void deveBuscarClientePorId() {
        fail("Teste não implementado");}

    void deveBuscarClientePorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    void deveAtualizarCliente() {
        fail("Teste não implementado");
    }

    void deveDeletarCliente() {
        fail("Teste não implementado");
    }
}
