package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento;

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
            throw new RuntimeException("Tipo de documento não pode ser nulo");
        }

        if(numeroDocumento == null) {
            throw new RuntimeException(String.format("Número do documento %s não pode ser nulo",
                    tipoDocumento.toString()));
        }

        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }
}
