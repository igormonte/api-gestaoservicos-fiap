package br.com.fiap.postech.gestaoservicos.core.domain.pessoa;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.Documento;
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
            throw new RuntimeException("Nome não pode ser nulo");
        }

        if(documento == null) {
            throw new RuntimeException("Documento não pode ser nulo");
        }

        if(dataNascimento == null) {
            throw new RuntimeException("Data de Nascimento não pode ser nulo");
        }

        if(email == null) {
            throw new RuntimeException("Email não pode ser nulo");
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
            throw new RuntimeException("Nome não pode ser nulo");
        }

        if(documento == null) {
            throw new RuntimeException("Documento não pode ser nulo");
        }

        if(dataNascimento == null) {
            throw new RuntimeException("Data de Nascimento não pode ser nulo");
        }

        if(email == null) {
            throw new RuntimeException("Email não pode ser nulo");
        }

        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
    }

}
