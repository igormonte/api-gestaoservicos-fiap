package br.com.fiap.postech.gestaoservicos.core.domain.profissional;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.NomeEspecialidadeNaoPodeSerNuloException;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class Especialidade {

    private UUID id;
    private String nome;
    private String descricao;
    private LocalTime duracao;
    public Especialidade(
            String nome,
            String descricao,
            LocalTime duracao) {

        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }
    

}
