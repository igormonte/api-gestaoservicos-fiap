package br.com.fiap.postech.gestaoservicos.adapter.dto.request.profissional;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.EspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;

import java.time.LocalDate;
import java.util.List;

public record RequestCadastrarProfissionalDto(
        String nome,
        String email,
        String numeroDocumento,
        TipoDocumento tipoDocumento,
        LocalDate dataNascimento,
        List<RequestEspecialidadeDto> especialidade) {
}
