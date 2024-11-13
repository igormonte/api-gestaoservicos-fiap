package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;

import java.util.List;
import java.util.UUID;

public interface BuscarEstabelecimentoUseCase {
    EstabelecimentoEntity buscarPorId(UUID id);

    EstabelecimentoEntity buscarPorCnpj(String cnpj);

    List<EstabelecimentoEntity> buscarDinamica(String especificacao);
}
