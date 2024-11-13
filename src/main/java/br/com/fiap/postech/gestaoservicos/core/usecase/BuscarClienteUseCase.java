package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;

import java.util.List;
import java.util.UUID;

public interface BuscarClienteUseCase {

    ClienteEntity porId(UUID id);

    List<ClienteEntity> todos();
}
