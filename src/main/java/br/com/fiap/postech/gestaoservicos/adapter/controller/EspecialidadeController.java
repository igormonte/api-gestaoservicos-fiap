package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.AssociarEspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.usecase.AssociarEspecialidadeProfissionalUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarEspecialidadeUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    private final CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase;
    private final AssociarEspecialidadeProfissionalUseCase associarEspecialidadeProfissionalUseCase;

    public EspecialidadeController(
            CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase,
            AssociarEspecialidadeProfissionalUseCase associarEspecialidadeProfissionalUseCase) {
        this.cadastrarEspecialidadeUseCase = cadastrarEspecialidadeUseCase;
        this.associarEspecialidadeProfissionalUseCase = associarEspecialidadeProfissionalUseCase;
    }

    @PostMapping(value = "")
    public Especialidade cadastrarEspecialidade(@RequestBody Especialidade especialidade) {
        return this.cadastrarEspecialidadeUseCase.execute(especialidade);
    }

    @PostMapping(value = "/associar")
    public Boolean associarEspecialidade(@RequestBody AssociarEspecialidadeDto associarEspecialidadeDto) {
        return this.associarEspecialidadeProfissionalUseCase.executar(
                associarEspecialidadeDto.idEspecialidade(),
                associarEspecialidadeDto.idProfissional());
    }

}
