package br.com.fiap.postech.gestaoservicos.core.usecase.repository;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;

import java.util.List;
import java.util.UUID;

public interface ProfissionalRepository {

    ProfissionalEntity salvarProfissional(ProfissionalEntity profissional);

    ProfissionalEntity buscarPorId(UUID idProfissional);

    List<ProfissionalEntity> todos();
}
