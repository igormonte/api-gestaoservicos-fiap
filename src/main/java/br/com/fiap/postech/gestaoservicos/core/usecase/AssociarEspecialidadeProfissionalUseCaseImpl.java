package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EspecialidadeRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;

import java.util.UUID;

public class AssociarEspecialidadeProfissionalUseCaseImpl implements AssociarEspecialidadeProfissionalUseCase {

    private final EspecialidadeRepository especialidadeRepository;
    private final ProfissionalRepository profissionalRepository;

    public AssociarEspecialidadeProfissionalUseCaseImpl(
            EspecialidadeRepository especialidadeRepository,
            ProfissionalRepository profissionalRepository) {
        this.especialidadeRepository = especialidadeRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @Override
    public Boolean executar(UUID idEspecialidade, UUID idProfissional) {

        Especialidade  especialidade = this.especialidadeRepository.buscarPorId(idEspecialidade);
        if(especialidade == null) {
            throw new RuntimeException("Especialidade não encontrada");
        }

        ProfissionalEntity profissional = this.profissionalRepository.buscarPorId(idProfissional);

        if(profissional == null) {
            throw new RuntimeException("Profissional não encontrado");
        }

        profissional.adicionarEspecialidade(especialidade);

        this.profissionalRepository.salvarProfissional(profissional);

        return true;


    }

}
