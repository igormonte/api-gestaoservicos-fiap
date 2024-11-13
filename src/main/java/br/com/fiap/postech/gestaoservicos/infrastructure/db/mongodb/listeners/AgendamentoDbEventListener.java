package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.listeners;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendamentoDb;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class AgendamentoDbEventListener extends AbstractMongoEventListener<AgendamentoDb> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<AgendamentoDb> event) {

        super.onBeforeConvert(event);
        AgendamentoDb entity = event.getSource();

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}