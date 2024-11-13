package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.ProfissionalDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.ProfissionalMapper;

import java.util.List;
import java.util.UUID;

public class ProfissionalDbGateway implements ProfissionalRepository {

    private final ProfissionalDbRepository profissionalDbRepository;
    private final ProfissionalMapper profissionalMapper;

    public ProfissionalDbGateway(
            ProfissionalDbRepository profissionalDbRepository,
            ProfissionalMapper profissionalMapper) {
        this.profissionalDbRepository = profissionalDbRepository;
        this.profissionalMapper = profissionalMapper;
    }

    @Override
    public ProfissionalEntity salvarProfissional(ProfissionalEntity profissional) {
        return this.profissionalMapper.toProfissionalEntity(
                this.profissionalDbRepository.save(
                        this.profissionalMapper.toProfissionalDbEntity(profissional)));
    }

    @Override
    public ProfissionalEntity buscarPorId(UUID idProfissional) {
        return this.profissionalMapper.toProfissionalEntity(
                this.profissionalDbRepository.findById(idProfissional).orElseThrow());
    }

    @Override
    public List<ProfissionalEntity> todos() {
        return this.profissionalMapper.toProfissionalEntityList(
                this.profissionalDbRepository.findAll());
    }

}
