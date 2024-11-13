package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class DocumentoTest {

    @Test
    void deveCriarDocumento() {
        String numeroDocumento = "12345678901";
        TipoDocumento tipoDocumento = TipoDocumento.CPF;

        Documento documento = new Documento(tipoDocumento, numeroDocumento);

        assertThat(documento.getTipoDocumento()).isEqualTo(tipoDocumento);
        assertThat(documento.getNumeroDocumento()).isEqualTo(numeroDocumento);
    }

    @Test
    void deveCriarDocumento_GeraExcessaoSeTipoDocumentoNulo() {
        String numeroDocumento = "12345678901";
        TipoDocumento tipoDocumento = null;

       assertThatThrownBy(() -> new Documento(tipoDocumento, numeroDocumento))
               .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deveCriarDocumento_GeraExcessaoSeNumeroDocumentoNulo() {
        String numeroDocumento = null;
        TipoDocumento tipoDocumento = TipoDocumento.CPF;

        assertThatThrownBy(() -> new Documento(tipoDocumento, numeroDocumento))
                .isInstanceOf(RuntimeException.class);
    }
}
