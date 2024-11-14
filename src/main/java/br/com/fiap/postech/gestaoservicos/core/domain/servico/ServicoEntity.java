package br.com.fiap.postech.gestaoservicos.core.domain.servico;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.exception.ClienteNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EstabelecimentoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.EspecialidadeNaoPodeSerNulaException;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.ProfissionalNaoPodeSerNuloException;
import lombok.Data;

import java.util.UUID;

@Data
public class ServicoEntity {

    private EstabelecimentoEntity estabelecimento;
    private Especialidade especialidade;
    private ProfissionalEntity profissional;
    private ClienteEntity cliente;
    private Avaliacao avaliacao;

    public ServicoEntity(
            EstabelecimentoEntity estabelecimento,
            ProfissionalEntity profissional,
            ClienteEntity cliente,
            Especialidade especialidade) {

        if(estabelecimento == null) {
            throw new EstabelecimentoNaoPodeSerNuloException();
        }

        if(profissional == null) {
            throw new ProfissionalNaoPodeSerNuloException();
        }

        if(cliente == null) {
            throw new ClienteNaoPodeSerNuloException();
        }

        if(especialidade == null) {
            throw new EspecialidadeNaoPodeSerNulaException();
        }

        this.estabelecimento = estabelecimento;
        this.profissional = profissional;
        this.cliente = cliente;
        this.especialidade = especialidade;
    }
}
