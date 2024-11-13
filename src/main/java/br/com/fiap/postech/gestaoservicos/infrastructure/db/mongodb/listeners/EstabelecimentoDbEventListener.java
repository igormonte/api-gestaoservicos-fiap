package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.listeners;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendamentoDb;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EstabelecimentoDbEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class EstabelecimentoDbEventListener extends AbstractMongoEventListener<EstabelecimentoDbEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<EstabelecimentoDbEntity> event) {

        super.onBeforeConvert(event);
        EstabelecimentoDbEntity entity = event.getSource();

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}