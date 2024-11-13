package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;

import java.util.UUID;

public interface AdicionarProfissionalEstabelecimentoUseCase {

    EstabelecimentoEntity executar(UUID idEstabelecimento,UUID idProfissional);
}
