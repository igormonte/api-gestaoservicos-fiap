package br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

public record ReponseEspecialidadeDto (
    UUID id,
    String nome,
    String descricao){
}
