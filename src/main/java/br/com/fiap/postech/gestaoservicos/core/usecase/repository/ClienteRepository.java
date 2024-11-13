package br.com.fiap.postech.gestaoservicos.core.usecase.repository;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {
    public ClienteEntity salvarCliente(ClienteEntity cliente);

    public ClienteEntity buscarPorId(UUID id);

    List<ClienteEntity> todos();
}
