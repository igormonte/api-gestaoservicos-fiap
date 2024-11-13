package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class CPFTest {

    @Test
    void deveCriarCPF() {
        String cpf = "12345678901";
        CPF cpfObj = new CPF(cpf);
        assertThat(cpfObj.getNumeroDocumento()).isEqualTo(cpf);
        assertThat(cpfObj.getTipoDocumento()).isEqualTo(TipoDocumento.CPF);
    }

//    @Test
//    void deveCriarCPF_GeraExcessaoSe() {
//        fail("Teste não implementado");
//    }
}
