package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.UUID;


@Data
@Document(collection = "especialidade")
public class EspecialidadeDb {

    @Id
    private UUID id;
    private String nome;
    private String descricao;
    private LocalTime duracao;

}
