package br.com.fiap.postech.gestaoservicos.core.domain.servico;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
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
            throw new RuntimeException("Estabelecimento não pode ser nulo");
        }

        if(profissional == null) {
            throw new RuntimeException("Profissional não pode ser nulo");
        }

        if(cliente == null) {
            throw new RuntimeException("Cliente não pode ser nulo");
        }

        if(especialidade == null) {
            throw new RuntimeException("Especialidade não pode ser nulo");
        }

        this.estabelecimento = estabelecimento;
        this.profissional = profissional;
        this.cliente = cliente;
        this.especialidade = especialidade;
    }
}
