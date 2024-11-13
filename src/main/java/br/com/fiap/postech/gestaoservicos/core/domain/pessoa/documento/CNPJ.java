package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento;

public class CNPJ extends Documento {

    public CNPJ(String numeroDocumento) {
        super(TipoDocumento.CNPJ, numeroDocumento);
    }
}
