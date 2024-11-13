package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaJuridica;

import java.time.LocalDate;

public class PessoaJuridicaHelper {

    public static PessoaJuridica getPessoaJuridica() {
        return PessoaJuridica.criar("Razão Social Teste", "admin@razaoteste.com", "12345678901234", LocalDate.of(1995, 2, 10));
    }

}
