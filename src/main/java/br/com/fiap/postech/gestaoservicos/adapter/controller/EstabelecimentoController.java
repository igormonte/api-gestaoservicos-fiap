package br.com.fiap.postech.gestaoservicos.adapter.controller;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.AdicionarProfissionalDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.CadastrarEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.ProfissionalDto;
import br.com.fiap.postech.gestaoservicos.core.usecase.AdicionarProfissionalEstabelecimentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.BuscarEstabelecimentoUseCase;
import br.com.fiap.postech.gestaoservicos.core.usecase.CadastrarEstabelecimentoUseCase;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.EstabelecimentoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    private final CadastrarEstabelecimentoUseCase cadastrarEstabelecimentoUseCase;
    private final BuscarEstabelecimentoUseCase buscarEstabelecimentoUseCase;
    private final AdicionarProfissionalEstabelecimentoUseCase adicionarProfissionalEstabelecimentoUseCase;
    private final EstabelecimentoMapper estabelecimentoMapper;

    public EstabelecimentoController(
            CadastrarEstabelecimentoUseCase cadastrarEstabelecimentoUseCase,
            BuscarEstabelecimentoUseCase buscarEstabelecimentoUseCase,
            AdicionarProfissionalEstabelecimentoUseCase adicionarProfissionalEstabelecimentoUseCase, EstabelecimentoMapper estabelecimentoMapper) {
        this.cadastrarEstabelecimentoUseCase = cadastrarEstabelecimentoUseCase;
        this.buscarEstabelecimentoUseCase = buscarEstabelecimentoUseCase;
        this.adicionarProfissionalEstabelecimentoUseCase = adicionarProfissionalEstabelecimentoUseCase;
        this.estabelecimentoMapper = estabelecimentoMapper;
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorEspecificacao(@RequestParam("search") String especificacao) {
        return ResponseEntity.ok(
                this.estabelecimentoMapper.toResponseEstabelecimentoDtoList(
                        this.buscarEstabelecimentoUseCase.buscarDinamica(especificacao))
        );
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(
                this.estabelecimentoMapper.toResponseEstabelecimentoDto(
                        this.buscarEstabelecimentoUseCase.buscarPorId(UUID.fromString(id)))
        );
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarEstabelecimento(@RequestBody CadastrarEstabelecimentoDto cadastrarEstabelecimentoDto) {
        return ResponseEntity.ok(
            this.estabelecimentoMapper.toResponseEstabelecimentoDto(
                    this.cadastrarEstabelecimentoUseCase.execute(
                            cadastrarEstabelecimentoDto.nome(),
                            this.estabelecimentoMapper.toEndereco(cadastrarEstabelecimentoDto.endereco()),
                            Stream.ofNullable(cadastrarEstabelecimentoDto.profissional())
                                    .flatMap(Collection::stream)
                                    .map(p->p.id())
                                    .toList(),
                            this.estabelecimentoMapper.toFuncionamentoList(cadastrarEstabelecimentoDto.funcionamento()),
                            cadastrarEstabelecimentoDto.foto()
                    )
            )
        );
    }


    @PostMapping("/profissional/adicionar")
    public ResponseEntity<?> adicionarProfissionalEstabelecimento(@RequestBody AdicionarProfissionalDto adicionarProfissionalDto) {
        return ResponseEntity.ok(
                this.estabelecimentoMapper.toResponseEstabelecimentoDto(
                        this.adicionarProfissionalEstabelecimentoUseCase.executar(
                                adicionarProfissionalDto.idEstabelecimento(),
                                adicionarProfissionalDto.idProfissional()
                        ))
        );
    }
}
