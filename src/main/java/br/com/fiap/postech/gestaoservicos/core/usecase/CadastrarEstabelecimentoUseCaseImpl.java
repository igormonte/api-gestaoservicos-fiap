package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;

public class CadastrarEstabelecimentoUseCaseImpl implements CadastrarEstabelecimentoUseCase {
    private final EstabelecimentoRepository estabelecimentoRepository;

    public CadastrarEstabelecimentoUseCaseImpl(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }
    @Override
    public EstabelecimentoEntity execute(EstabelecimentoEntity estabelecimento) {
        return this.estabelecimentoRepository.criarEstabelecimento(estabelecimento);
    }
}
