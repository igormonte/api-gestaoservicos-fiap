package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaJuridica;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.Avaliacao;

import java.time.LocalDate;

public class ClienteHelper {

    public static ClienteEntity getCliente() {
        Pessoa pessoa = PessoaJuridica.criar("João da Silva", "joao.silva@exemplo.com", "12.312.312/0001-10", LocalDate.of(1995, 2, 10));
        return new ClienteEntity(pessoa);

    }
}
