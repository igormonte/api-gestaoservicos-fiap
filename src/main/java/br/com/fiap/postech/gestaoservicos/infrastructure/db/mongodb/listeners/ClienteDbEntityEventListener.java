package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.listeners;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class ClienteDbEntityEventListener extends AbstractMongoEventListener<ClienteDbEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ClienteDbEntity> event) {

        super.onBeforeConvert(event);
        ClienteDbEntity entity = event.getSource();

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}