package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "agenda")
public class AgendaDb {

    private List<AgendamentoDb> agendamento;

}
