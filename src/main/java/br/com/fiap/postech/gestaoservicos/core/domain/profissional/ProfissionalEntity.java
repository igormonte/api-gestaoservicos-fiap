package br.com.fiap.postech.gestaoservicos.core.domain.profissional;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.DocumentoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.NomeNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.PessoaNaoPodeSerNulaException;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.EspecialidadeJaExisteException;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.EspecialidadeNaoPodeSerNulaException;
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
            throw new PessoaNaoPodeSerNulaException();
        }

        if(pessoa.getDocumento() == null) {
            throw new DocumentoNaoPodeSerNuloException();
        }

        this.pessoa = pessoa;
        this.especialidade = especialidade;
        this.agenda = agenda;
    }

    public ProfissionalEntity(Pessoa pessoa) {
        if(pessoa == null) {
            throw new PessoaNaoPodeSerNulaException();
        }

        if(pessoa.getNome() == null) {
            throw new NomeNaoPodeSerNuloException();
        }

        if(pessoa.getDocumento() == null) {
            throw new DocumentoNaoPodeSerNuloException();
        }

        this.pessoa = pessoa;
    }

    public void adicionarEspecialidade(Especialidade especialidade) {
        if(especialidade == null) {
            throw new EspecialidadeNaoPodeSerNulaException();
        }

        if(this.especialidade == null) {
            this.especialidade = new LinkedList<>();
        }

        Boolean existeEspecialidade =
                this.especialidade.stream().anyMatch(e -> e.getId().equals(especialidade.getId()));

        if(existeEspecialidade) {
            throw new EspecialidadeJaExisteException();
        }

        this.especialidade.add(especialidade);
    }

}
