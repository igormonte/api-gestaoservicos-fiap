package br.com.fiap.postech.gestaoservicos.core.domain.pessoa;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CPF;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.Documento;

import java.time.LocalDate;
import java.util.UUID;

public class PessoaFisica extends Pessoa {

    private PessoaFisica(
            UUID id,
            String nome,
            String email,
            CPF cpf,
            LocalDate dataNascimento) {
        super(id, nome, email, cpf, dataNascimento);
    }

    private PessoaFisica(
            String nome,
            String email,
            CPF cpf,
            LocalDate dataNascimento) {
        super(nome, email, cpf, dataNascimento);

    }

    public static PessoaFisica criar(
            UUID id,
            String nome,
            String email,
            String numeroDocumento,
            LocalDate dataNascimento
    ) {
        return new PessoaFisica(
                id,
                nome,
                email,
                new CPF(numeroDocumento),
                dataNascimento
        );
    }

    public static PessoaFisica criar(
        UUID id,
        String nome,
        String email,
        CPF cpf,
        LocalDate dataNascimento
    ) {
        return new PessoaFisica(
            id,
            nome,
            email,
            cpf,
            dataNascimento
        );
    }

    public static PessoaFisica criar(
            String nome,
            String email,
            String numeroDocumento,
            LocalDate dataNascimento
    ) {
        return new PessoaFisica(
                nome,
                email,
                new CPF(numeroDocumento),
                dataNascimento
        );
    }

    public static PessoaFisica criar(
            String nome,
            String email,
            CPF cpf,
            LocalDate dataNascimento
    ) {
        return new PessoaFisica(
                nome,
                email,
                cpf,
                dataNascimento
        );
    }

}
