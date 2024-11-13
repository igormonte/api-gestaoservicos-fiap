package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EspecialidadeRepository;

public class CadastrarEspecialidadeUseCaseImpl implements CadastrarEspecialidadeUseCase {

    private final EspecialidadeRepository especialidadeRepository;
    public CadastrarEspecialidadeUseCaseImpl(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Especialidade execute(Especialidade especialidade) {
        return this.especialidadeRepository.criarEspecialidade(especialidade);
    }

}
