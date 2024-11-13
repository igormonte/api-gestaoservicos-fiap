package br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendaDb;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProfissionalDto (
    UUID id,
    String nome,
    String email,
    LocalDate dataNascimento,
    TipoDocumento tipoDocumento,
    String numeroDocumento,
    List<EspecialidadeDto> especialidade,
    AgendaDb agenda){


}
