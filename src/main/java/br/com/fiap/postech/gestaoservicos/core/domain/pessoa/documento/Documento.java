package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.NumeroDoDocumentoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.TipoDocumentoNaoPodeSerNuloException;
import lombok.Data;

@Data
public class Documento {
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

    public Documento(
        TipoDocumento tipoDocumento,
        String numeroDocumento
    ) {

        if(tipoDocumento == null) {
            throw new TipoDocumentoNaoPodeSerNuloException();
        }

        if(numeroDocumento == null) {
            throw new NumeroDoDocumentoNaoPodeSerNuloException(String.format("Número do documento %s não pode ser nulo",
                    tipoDocumento.toString()));
        }

        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }
}
