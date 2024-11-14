package br.com.fiap.postech.gestaoservicos.core.domain.pessoa;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.Documento;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.DataNascimentoNaoPodeSerNulaException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.DocumentoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.EmailNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.NomeNaoPodeSerNuloException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Pessoa {

    private UUID id;
    private String nome;
    private Documento documento;
    private LocalDate dataNascimento;
    private String email;

    public Pessoa() {

    }

    protected Pessoa(
            UUID id,
            String nome,
            String email,
            Documento documento,
            LocalDate dataNascimento
    ) {

        if(nome == null) {
            throw new NomeNaoPodeSerNuloException();
        }

        if(documento == null) {
            throw new DocumentoNaoPodeSerNuloException();
        }

        if(dataNascimento == null) {
            throw new DataNascimentoNaoPodeSerNulaException();
        }

        if(email == null) {
            throw new EmailNaoPodeSerNuloException();
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
    }

    protected Pessoa(
            String nome,
            String email,
            Documento documento,
            LocalDate dataNascimento
    ) {

        if(nome == null) {
            throw new NomeNaoPodeSerNuloException();
        }

        if(documento == null) {
            throw new DocumentoNaoPodeSerNuloException();
        }

        if(dataNascimento == null) {
            throw new DataNascimentoNaoPodeSerNulaException();
        }

        if(email == null) {
            throw new EmailNaoPodeSerNuloException();
        }

        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
    }

}
