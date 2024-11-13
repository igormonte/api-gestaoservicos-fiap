package br.com.fiap.postech.gestaoservicos.infrastructure.config;

import br.com.fiap.postech.gestaoservicos.adapter.gateway.*;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.*;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository.DynamicCriteriaBuilder;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EstabelecimentoDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.*;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {

    @Bean
    ClienteRepository getClienteRepository(
            ClienteDbRepository clienteDbRepository,
            ClienteMapper clienteMapper){
        return new ClienteDbGateway(
                clienteDbRepository, clienteMapper
        );
    }

    @Bean
    EstabelecimentoRepository getEstabelecimentoRepository(
            MongoTemplate mongoTemplate,
            DynamicCriteriaBuilder<EstabelecimentoDbEntity> dynamicCriteriaBuilder,
            EstabelecimentoDbRepository estabelecimentoDbRepository,
            EstabelecimentoMapper estabelecimentoMapper
    ){
        return new EstabelecimentoDbGateway(
                mongoTemplate, dynamicCriteriaBuilder, estabelecimentoDbRepository, estabelecimentoMapper
        );
    }

    @Bean
    ProfissionalRepository getProfissionalRepository(
            ProfissionalDbRepository profissionalDbRepository,
            ProfissionalMapper profissionalMapper
    ){
        return new ProfissionalDbGateway(
                profissionalDbRepository, profissionalMapper
        );
    }

    @Bean
    AgendamentoRepository getAgendamentoRepository(
            AgendamentoDbRepository agendamentoDbRepository,
            AgendamentoMapper agendamentoMapper
    ) {
        return new AgendamentoDbGateway(
                agendamentoDbRepository,
                agendamentoMapper
        );
    }

    @Bean
    EspecialidadeRepository getEspecialidadeRepository(
            EspecialidadeDbRepository especialidadeDbRepository,
            EspecialidadeMapper especialidadeMapper
    ) {
        return new EspecialidadeDbGateway(
                especialidadeDbRepository,
                especialidadeMapper
        );
    }

}
