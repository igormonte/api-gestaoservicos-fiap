package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;


import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfissionalDbRepository extends MongoRepository<ProfissionalDbEntity, UUID> {
}
