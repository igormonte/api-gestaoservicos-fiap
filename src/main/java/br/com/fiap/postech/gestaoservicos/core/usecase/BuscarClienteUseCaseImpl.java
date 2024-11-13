package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;

import java.util.List;
import java.util.UUID;

public class BuscarClienteUseCaseImpl implements BuscarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public BuscarClienteUseCaseImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteEntity porId(UUID id) {
        return this.clienteRepository.buscarPorId(id);
    }

    @Override
    public List<ClienteEntity> todos() {
        return this.clienteRepository.todos();
    }
}
