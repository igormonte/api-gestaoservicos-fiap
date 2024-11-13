package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.cliente.RequestCadastrarClienteDto;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarClienteUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarClienteUseCase;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;

    public ClienteController(
            CadastrarClienteUseCase cadastrarClienteUseCase,
            BuscarClienteUseCase buscarClienteUseCase) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
    }

    @GetMapping(value = "/buscar/{id}")
    public ClienteEntity buscarClientePorId(@PathVariable String id) {
        return this.buscarClienteUseCase.porId(UUID.fromString(id));
    }

    @GetMapping(value = "/buscar")
    public List<ClienteEntity> buscarTodos() {
        return this.buscarClienteUseCase.todos();
    }

    @PostMapping(value = "")
    public ClienteEntity cadastrarCliente(@RequestBody RequestCadastrarClienteDto cliente) {
        return this.cadastrarClienteUseCase.executa(
                cliente.nome(),
                cliente.email(),
                cliente.numeroDocumento(),
                cliente.tipoDocumento(),
                cliente.dataNascimento());
    }

}
