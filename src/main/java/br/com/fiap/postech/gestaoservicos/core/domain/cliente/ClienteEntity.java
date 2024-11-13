package br.com.fiap.postech.gestaoservicos.core.domain.cliente;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agenda;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ClienteEntity {

    private UUID id;
    private Pessoa pessoa;

    public ClienteEntity() {
    }

    public ClienteEntity(
        Pessoa pessoa
    ) {
        this.pessoa = pessoa;
    }

}
