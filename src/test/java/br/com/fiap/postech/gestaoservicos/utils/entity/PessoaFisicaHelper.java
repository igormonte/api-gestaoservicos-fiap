package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaFisica;

import java.time.LocalDate;

public class PessoaFisicaHelper {

    public static PessoaFisica criaPessoaFisica() {
        return PessoaFisica.criar("João", "joao@exemplo.com", "1234567890", LocalDate.of(1990, 5, 15));
    }

}
