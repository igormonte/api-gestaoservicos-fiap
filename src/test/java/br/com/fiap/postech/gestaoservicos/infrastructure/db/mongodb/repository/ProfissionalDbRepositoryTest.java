package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Fail.fail;

public class ProfissionalDbRepositoryTest {

    @Mock
    private ProfissionalDbRepository profissionalDbRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    void deveSalvarProfissional() {
        fail("Teste não implementado");
    }

    void deveBuscarProfissionalPorId() {
        fail("Teste não implementado");}

    void deveBuscarProfissionalPorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    void deveAtualizarProfissional() {
        fail("Teste não implementado");
    }

    void deveDeletarProfissional() {
        fail("Teste não implementado");
    }

}
