package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.Documento;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;


@Data
@Document(collection = "cliente")
public class ClienteDbEntity {

    @Id
    private UUID id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

}
