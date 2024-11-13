package br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendaDb;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EspecialidadeDb;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ResponseProfissionalDto (
    UUID id,
    String nome,
    String email,
    LocalDate dataNascimento,
    TipoDocumento tipoDocumento,
    String numeroDocumento,
    List<ReponseEspecialidadeDto> especialidade,
    AgendaDb agenda) {


}
