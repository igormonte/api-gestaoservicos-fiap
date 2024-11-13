package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
public class EnderecoDb {

    @Id
    private UUID id;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

}
