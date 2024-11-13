package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.listeners;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendamentoDb;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EspecialidadeDb;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class EspecialidadeDbEventListener extends AbstractMongoEventListener<EspecialidadeDb> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<EspecialidadeDb> event) {

        super.onBeforeConvert(event);
        EspecialidadeDb entity = event.getSource();

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}