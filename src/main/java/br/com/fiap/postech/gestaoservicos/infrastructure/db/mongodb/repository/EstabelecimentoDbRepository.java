package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;


import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EstabelecimentoDbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstabelecimentoDbRepository extends MongoRepository<EstabelecimentoDbEntity, UUID> {
}
