package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;

public interface CadastrarEstabelecimentoUseCase {

    EstabelecimentoEntity  execute(EstabelecimentoEntity estabelecimento);
}
