package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class CNPJTest {

    @Test
    void deveCriarCNPJ() {
        String cnpj = "12345678901";
        CNPJ cnpjObj = new CNPJ(cnpj);
        assertThat(cnpjObj.getNumeroDocumento()).isEqualTo(cnpj);
        assertThat(cnpjObj.getTipoDocumento()).isEqualTo(TipoDocumento.CNPJ);
    }

//    @Test
//    void deveCriarCNPJ_GeraExcessaoSe() {
//        fail("Teste não implementado");
//    }

}
