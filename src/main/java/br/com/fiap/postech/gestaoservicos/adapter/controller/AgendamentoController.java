package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento.RealizarAgendamentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.agendamento.RealizarAvaliacaoDto;
import br.com.fiap.postech.gestaoservicos.adapter.facade.ExportaAgendamentoFacade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscaAgendamentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.RealizarAgendamentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.RealizarAvaliacaoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final RealizarAgendamentoUseCase realizarAgendamentoUseCase;
    private final RealizarAvaliacaoUseCase realizarAvaliacaoUseCase;
    private final ExportaAgendamentoFacade exportaAgendamentoFacade;
    private final BuscaAgendamentoUseCase buscaAgendamentoUseCase;

    public AgendamentoController(
            RealizarAgendamentoUseCase realizarAgendamentoUseCase,
            RealizarAvaliacaoUseCase realizarAvaliacaoUseCase,
            ExportaAgendamentoFacade exportaAgendamentoFacade,
            BuscaAgendamentoUseCase buscaAgendamentoUseCase) {
        this.realizarAgendamentoUseCase = realizarAgendamentoUseCase;
        this.realizarAvaliacaoUseCase = realizarAvaliacaoUseCase;
        this.exportaAgendamentoFacade = exportaAgendamentoFacade;
        this.buscaAgendamentoUseCase = buscaAgendamentoUseCase;
    }

    @PostMapping(value = "")
    public Agendamento realizarAgendamento(@RequestBody RealizarAgendamentoDto realizarAgendamentoDto) {
        return this.realizarAgendamentoUseCase.executar(
                realizarAgendamentoDto.idCliente(),
                realizarAgendamentoDto.idEstabelecimento(),
                realizarAgendamentoDto.idProfissional(),
                realizarAgendamentoDto.idEspecialidade(),
                realizarAgendamentoDto.dataHora());
    }

    @GetMapping
    public List<Agendamento> buscarAgendamentos() {
        return this.buscaAgendamentoUseCase.todos();
    }

    @PostMapping(value = "/avaliar")
    public Agendamento realizarAvaliacao(@RequestBody RealizarAvaliacaoDto realizarAvaliacaoDto) {
        return this.realizarAvaliacaoUseCase.executar(
                realizarAvaliacaoDto.idCliente(),
                realizarAvaliacaoDto.idAgendamento(),
                realizarAvaliacaoDto.nota(),
                realizarAvaliacaoDto.comentario());
    }

    @GetMapping(value = "/exportar/cliente/{idCliente}", produces = "application/ics")
    public ResponseEntity<?> exportarAgendaCliente(@PathVariable String idCliente) {
        return ResponseEntity.ok(
                this.exportaAgendamentoFacade.exportaAgendamentoCliente(UUID.fromString(idCliente)));
    }

    @GetMapping(value = "/exportar/profissional/{idProfissional}", produces = "application/ics")
    public ResponseEntity<?> exportarAgendaProfissional(@PathVariable String idProfissional) {
        return ResponseEntity.ok(
                this.exportaAgendamentoFacade.exportaAgendamentoProfissional(UUID.fromString(idProfissional)));
    }

}
