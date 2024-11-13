package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository.DynamicCriteriaBuilder;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EstabelecimentoDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.EstabelecimentoDbRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.EstabelecimentoMapper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.UUID;

public class EstabelecimentoDbGateway implements EstabelecimentoRepository {

    private final MongoTemplate mongoTemplate;
    private final DynamicCriteriaBuilder<EstabelecimentoDbEntity> dynamicCriteriaBuilder;
    private final EstabelecimentoDbRepository estabelecimentoDbRepository;
    private final EstabelecimentoMapper estabelecimentoMapper;

    public EstabelecimentoDbGateway(
            MongoTemplate mongoTemplate,
            DynamicCriteriaBuilder<EstabelecimentoDbEntity> dynamicCriteriaBuilder,
            EstabelecimentoDbRepository estabelecimentoDbRepository,
            EstabelecimentoMapper estabelecimentoMapper) {
        this.mongoTemplate = mongoTemplate;
        this.dynamicCriteriaBuilder = dynamicCriteriaBuilder;
        this.estabelecimentoDbRepository = estabelecimentoDbRepository;
        this.estabelecimentoMapper = estabelecimentoMapper;
    }

    @Override
    public EstabelecimentoEntity buscarPorId(UUID id) {
        return this.estabelecimentoMapper.toEstabelecimentoEntity(
                this.estabelecimentoDbRepository.findById(id).orElseThrow());
    }

    @Override
    public EstabelecimentoEntity buscarPorCnpj(String cnpj) {
        return null;
    }

    @Override
    public EstabelecimentoEntity criarEstabelecimento(EstabelecimentoEntity estabelecimento) {
        return this.estabelecimentoMapper.toEstabelecimentoEntity(
                this.estabelecimentoDbRepository.save(
                        this.estabelecimentoMapper.toEstabelecimentoDbEntity(estabelecimento)));
    }

    @Override
    public List<EstabelecimentoEntity> buscarDinamica(String especificacao) {
        return this.estabelecimentoMapper.toEstabelecimentoEntityList(
                this.mongoTemplate.find(
                        this.dynamicCriteriaBuilder.build(especificacao), EstabelecimentoDbEntity.class));
    }

    @Override
    public EstabelecimentoEntity atualizarEstabelecimento(EstabelecimentoEntity estabelecimento) {
        return this.estabelecimentoMapper.toEstabelecimentoEntity(
                this.estabelecimentoDbRepository.save(
                        this.estabelecimentoMapper.toEstabelecimentoDbEntity(estabelecimento)));

    }
}
