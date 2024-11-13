package br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento;

public class CPF extends Documento {

    public CPF(String numeroDocumento) {
        super(TipoDocumento.CPF, numeroDocumento);
    }
}
