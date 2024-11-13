package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.listeners;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class ProfissionalDbEntityEventListener extends AbstractMongoEventListener<ProfissionalDbEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ProfissionalDbEntity> event) {

        super.onBeforeConvert(event);
        ProfissionalDbEntity entity = event.getSource();

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}