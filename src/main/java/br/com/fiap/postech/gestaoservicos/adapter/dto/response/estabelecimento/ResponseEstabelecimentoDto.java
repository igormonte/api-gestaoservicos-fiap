package br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EnderecoDb;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

public record ResponseEstabelecimentoDto (
    UUID id,
    String nome,
    EnderecoDb endereco,
    List<ResponseProfissionalDto> profissional,
    List<ResponseFuncionamentoDto> funcionamento,
    List<String> foto) {

}
