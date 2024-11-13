package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;

import java.util.List;
import java.util.UUID;

public class BuscarEstabelecimentoUseCaseImpl implements BuscarEstabelecimentoUseCase {

    private final EstabelecimentoRepository estabelecimentoRepository;

    public BuscarEstabelecimentoUseCaseImpl(
            EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    public EstabelecimentoEntity buscarPorId(UUID id) {
        return estabelecimentoRepository.buscarPorId(id);
    }

    @Override
    public EstabelecimentoEntity buscarPorCnpj(String cnpj) {
        return null;
    }

    @Override
    public List<EstabelecimentoEntity> buscarDinamica(String especificacao) {
        return estabelecimentoRepository.buscarDinamica(especificacao);
    }

}
