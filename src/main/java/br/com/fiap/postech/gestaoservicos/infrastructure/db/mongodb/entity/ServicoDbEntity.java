package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.Avaliacao;
import lombok.Data;

@Data
public class ServicoDbEntity {

    private EstabelecimentoDbEntity estabelecimento;
    private EspecialidadeDb especialidade;
    private ProfissionalDbEntity profissional;
    private ClienteDbEntity cliente;
    private Avaliacao avaliacao;

}
