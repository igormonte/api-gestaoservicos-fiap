package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;

import java.util.List;
import java.util.UUID;

public class BuscarProfissionalUseCaseImpl implements BuscarProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;

    public BuscarProfissionalUseCaseImpl(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    @Override
    public ProfissionalEntity porId(UUID id) {
        return this.profissionalRepository.buscarPorId(id);
    }

    @Override
    public List<ProfissionalEntity> todos() {
        return this.profissionalRepository.todos();
    }
}
