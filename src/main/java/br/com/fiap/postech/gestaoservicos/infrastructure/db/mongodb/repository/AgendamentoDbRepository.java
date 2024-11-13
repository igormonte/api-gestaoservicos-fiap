package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendamentoDb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgendamentoDbRepository extends MongoRepository<AgendamentoDb, UUID> {

    @Query("{ 'servico.profissional.id': ?0, 'dataHora': { $gte: ?1, $lte: ?2} } }")
    List<AgendamentoDb> findByServicoProfissionalIdAndDataHoraInicialAndDataHoraFinal(
            UUID idProfissional, LocalDateTime dataInicial, LocalDateTime dataFinal);


    @Query("{ 'servico.cliente.id': ?0, 'dataHora': { $gte: ?1} }")
    List<AgendamentoDb> findByClienteIdAndDataHoraInicial(
            UUID idCliente, LocalDateTime dataInicial);

    @Query("{ 'servico.profissional.id': ?0, 'dataHora': { $gte: ?1} } }")
    List<AgendamentoDb> findByProfissionalIdAndDataHoraInicial(
            UUID idProfissional, LocalDateTime dataInicial);

}
