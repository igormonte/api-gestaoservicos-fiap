package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.EspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional.RequestCadastrarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarPerfilProfissionalUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    private final CadastrarPerfilProfissionalUseCase cadastrarPerfilProfissionalUseCase;

    public ProfissionalController(CadastrarPerfilProfissionalUseCase cadastrarPerfilProfissionalUseCase) {
        this.cadastrarPerfilProfissionalUseCase = cadastrarPerfilProfissionalUseCase;
    }

    @PostMapping(value = "")
    public ProfissionalEntity cadastrarProfissional(
            @RequestBody RequestCadastrarProfissionalDto profissional) {
        return this.cadastrarPerfilProfissionalUseCase.executar(
                profissional.nome(),
                profissional.email(),
                profissional.numeroDocumento(),
                profissional.tipoDocumento(),
                profissional.dataNascimento(),
                Optional.ofNullable(profissional.especialidade())
                    .orElseGet(Collections::emptyList).stream().map(e->e.id()).toList());
    }

}
