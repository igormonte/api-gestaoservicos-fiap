package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;

import java.util.List;
import java.util.UUID;

public interface BuscarProfissionalUseCase {

    ProfissionalEntity porId(UUID id);

    List<ProfissionalEntity> todos();
}
