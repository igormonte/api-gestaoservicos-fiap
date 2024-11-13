package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CNPJ;

public class CNPJHelper {

    public static CNPJ getCNJP() {
        return new CNPJ("12.123.123/0001-30");
    }

}
