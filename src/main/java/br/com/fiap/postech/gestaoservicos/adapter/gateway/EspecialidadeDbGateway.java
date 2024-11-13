package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EspecialidadeRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.EspecialidadeDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.EspecialidadeMapper;

import java.util.List;
import java.util.UUID;

public class EspecialidadeDbGateway implements EspecialidadeRepository {

    private final EspecialidadeDbRepository especialidadeDbRepository;
    private final EspecialidadeMapper especialidadeMapper;

    public EspecialidadeDbGateway(
            EspecialidadeDbRepository especialidadeDbRepository,
            EspecialidadeMapper especialidadeMapper) {
        this.especialidadeDbRepository = especialidadeDbRepository;
        this.especialidadeMapper = especialidadeMapper;
    }

    @Override
    public Especialidade criarEspecialidade(Especialidade especialidade) {
        return this.especialidadeMapper.toEspecialidade(
                this.especialidadeDbRepository.save(
                        this.especialidadeMapper.toEspecialidadeDb(especialidade)));
    }

    @Override
    public Especialidade buscarPorId(UUID idEspecialidade) {
        return this.especialidadeMapper.toEspecialidade(
                this.especialidadeDbRepository.findById(idEspecialidade).orElseThrow());
    }

    @Override
    public List<Especialidade> buscarEspecialidadesPorIds(List<UUID> idEspecialidadeList) {
        return this.especialidadeMapper.toEspecialidadeList(
                this.especialidadeDbRepository.findAllById(idEspecialidadeList));
    }
}
