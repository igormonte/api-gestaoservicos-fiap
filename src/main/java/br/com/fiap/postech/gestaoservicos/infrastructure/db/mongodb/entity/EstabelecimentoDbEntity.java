package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "estabelecimento")
public class EstabelecimentoDbEntity {
    @Id
    private UUID id;
    private String nome;
    private EnderecoDb endereco;
    private List<ProfissionalDbEntity> profissional;
    private List<FuncionamentoDb> funcionamento;
    private List<String> foto;

}
