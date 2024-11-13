package br.com.fiap.postech.gestaoservicos.core.usecase.repository;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;

import java.util.List;
import java.util.UUID;

public interface EstabelecimentoRepository {

    EstabelecimentoEntity buscarPorId(UUID id);
    EstabelecimentoEntity buscarPorCnpj(String cnpj);
    EstabelecimentoEntity criarEstabelecimento(EstabelecimentoEntity estabelecimento);
    List<EstabelecimentoEntity> buscarDinamica(String especificacao);
    EstabelecimentoEntity atualizarEstabelecimento(EstabelecimentoEntity estabelecimento);
}
