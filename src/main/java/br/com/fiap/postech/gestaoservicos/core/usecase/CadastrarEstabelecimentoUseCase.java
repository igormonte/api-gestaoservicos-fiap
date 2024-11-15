package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Endereco;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;

import java.util.List;
import java.util.UUID;

public interface CadastrarEstabelecimentoUseCase {

    EstabelecimentoEntity execute(
            String nome,
            Endereco endereco,
            List<UUID> idProfissional,
            List<Funcionamento> funcionamento,
            List<String> foto);
}
