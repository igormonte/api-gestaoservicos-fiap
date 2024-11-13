package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Fail.fail;

public class EstabelecimentoDbRepositoryTest {

    @Mock
    private EstabelecimentoDbRepository EstabelecimentoDbRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    void deveSalvarEstabelecimento() {
        fail("Teste não implementado");
    }

    void deveBuscarEstabelecimentoPorId() {
        fail("Teste não implementado");}

    void deveBuscarEstabelecimentoPorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    void deveAtualizarEstabelecimento() {
        fail("Teste não implementado");
    }

    void deveDeletarEstabelecimento() {
        fail("Teste não implementado");
    }
}
