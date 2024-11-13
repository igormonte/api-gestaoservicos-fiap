package br.com.fiap.postech.gestaoservicos.core.domain.pessoa;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CNPJ;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CPF;

import java.time.LocalDate;
import java.util.UUID;

public class PessoaJuridica extends Pessoa {

    private PessoaJuridica(
            UUID id,
            String nome,
            String email,
            CNPJ cnpj,
            LocalDate dataNascimento) {
        super(id, nome, email, cnpj, dataNascimento);
    }

    public static PessoaJuridica criar(
            String nome,
            String email,
            String numeroDocumento,
            LocalDate dataNascimento
    ) {
        return new PessoaJuridica(
                null,
                nome,
                email,
                new CNPJ(numeroDocumento),
                dataNascimento
        );
    }


    public static PessoaJuridica criar(
            UUID id,
            String nome,
            String email,
            CNPJ cnpj,
            LocalDate dataNascimento
    ) {
        return new PessoaJuridica(
                id,
                nome,
                email,
                cnpj,
                dataNascimento
        );
    }


}
