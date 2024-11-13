package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Document(collection = "agendamento")
public class AgendamentoDb {

    @Id
    private UUID id;
    private LocalDateTime dataHora;
    private LocalTime duracao;
    private ServicoDbEntity servico;

}
