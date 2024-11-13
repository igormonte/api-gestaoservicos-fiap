package br.com.fiap.postech.gestaoservicos.core.usecase.repository;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;

import java.util.List;
import java.util.UUID;

public interface EspecialidadeRepository {

    Especialidade criarEspecialidade(Especialidade especialidade);

    Especialidade buscarPorId(UUID idEspecialidade);

    List<Especialidade> buscarEspecialidadesPorIds(List<UUID> idEspecialidadeList);
}
