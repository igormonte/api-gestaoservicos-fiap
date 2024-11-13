package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;

import java.util.UUID;

public class AdicionarProfissionalEstabelecimentoUseCaseImpl implements AdicionarProfissionalEstabelecimentoUseCase {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProfissionalRepository profissionalRepository;

    public AdicionarProfissionalEstabelecimentoUseCaseImpl(
            EstabelecimentoRepository estabelecimentoRepository,
            ProfissionalRepository profissionalRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public EstabelecimentoEntity executar(UUID idEstabelecimento,UUID idProfissional) {

        EstabelecimentoEntity estabelecimento = estabelecimentoRepository.buscarPorId(idEstabelecimento);

        if (estabelecimento == null) {
            throw new RuntimeException("Estabelecimento não encontrado");
        }

        ProfissionalEntity profissional = profissionalRepository.buscarPorId(idProfissional);

        if (profissional == null) {
            throw new RuntimeException("Profissional não encontrado");
        }

        estabelecimento.adicionarProfissional(profissional);

        return estabelecimentoRepository.atualizarEstabelecimento(estabelecimento);

    }
}
