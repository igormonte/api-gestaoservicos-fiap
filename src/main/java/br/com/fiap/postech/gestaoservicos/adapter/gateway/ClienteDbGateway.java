package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.ClienteDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.ClienteMapper;

import java.util.List;
import java.util.UUID;

public class ClienteDbGateway implements ClienteRepository {

    private final ClienteDbRepository clienteDbRepository;
    private final ClienteMapper clienteMapper;

    public ClienteDbGateway(
            ClienteDbRepository clienteDbRepository,
            ClienteMapper clienteMapper) {
        this.clienteDbRepository = clienteDbRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteEntity salvarCliente(ClienteEntity cliente) {
        return this.clienteMapper.toClienteEntity(
                this.clienteDbRepository.save(
                        this.clienteMapper.toClienteDbEntity(cliente)));
    }

    @Override
    public ClienteEntity buscarPorId(UUID id) {
        return this.clienteMapper.toClienteEntity(
                this.clienteDbRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ClienteEntity> todos() {
        return this.clienteMapper.toClienteEntityList(
                this.clienteDbRepository.findAll());
    }
}
