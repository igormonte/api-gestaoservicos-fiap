package br.com.fiap.postech.gestaoservicos.core.domain.profissional;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
public class ProfissionalEntity {

    private UUID id;
    private Pessoa pessoa;
    private List<Especialidade> especialidade;
    private Agenda agenda;

    public ProfissionalEntity() {
    }

    public ProfissionalEntity(
        Pessoa pessoa,
        List<Especialidade> especialidade,
        Agenda agenda
    ) {
        if(pessoa == null) {
            throw new RuntimeException("Pessoa não pode ser nula");
        }

        if(pessoa.getDocumento() == null) {
            throw new RuntimeException("Documento não pode ser nulo");
        }

        this.pessoa = pessoa;
        this.especialidade = especialidade;
        this.agenda = agenda;
    }

    public ProfissionalEntity(Pessoa pessoa) {
        if(pessoa == null) {
            throw new RuntimeException("Pessoa não pode ser nula");
        }

        if(pessoa.getNome() == null) {
            throw new RuntimeException("Nome não pode ser nulo");
        }

        if(pessoa.getDocumento() == null) {
            throw new RuntimeException("Documento não pode ser nulo");
        }

        this.pessoa = pessoa;
    }

    public void adicionarEspecialidade(Especialidade especialidade) {
        if(especialidade == null) {
            throw new RuntimeException("Especialidade não pode ser nula");
        }

        if(this.especialidade == null) {
            this.especialidade = new LinkedList<>();
        }

        Boolean existeEspecialidade =
                this.especialidade.stream().anyMatch(e -> e.getId().equals(especialidade.getId()));

        if(existeEspecialidade) {
            throw new RuntimeException("Especialidade já existe");
        }

        this.especialidade.add(especialidade);
    }

}
