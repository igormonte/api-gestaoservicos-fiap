package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CPF;

public class CPFHelper {

    public static CPF getCPF() {
        return new CPF("123.123.123-30");
    }

}
