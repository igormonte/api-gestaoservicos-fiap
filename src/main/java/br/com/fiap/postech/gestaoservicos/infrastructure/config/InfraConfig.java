package br.com.fiap.postech.gestaoservicos.infrastructure.config;

import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.CriteriaContext;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository.DynamicCriteriaBuilder;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository.ValueParser;
import br.com.fiap.postech.gestaoservicos.infrastructure.criteriaparser.ValueParserImpl;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InfraConfig {

    @Bean
    ValueParser getValueParser() {
        return new ValueParserImpl();
    }

    @Bean
    DynamicCriteriaBuilder<AgendaDb> getCriteriaBuilderAgenda(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(AgendaDb.class, context, valueParser);
    }

    @Bean
    DynamicCriteriaBuilder<AgendamentoDb> getCriteriaBuilderAgendamento(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(AgendamentoDb.class, context, valueParser);
    }


    @Bean
    DynamicCriteriaBuilder<ClienteDbEntity> getCriteriaBuilderCliente(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(ClienteDbEntity.class, context, valueParser);
    }

    @Bean
    DynamicCriteriaBuilder<EnderecoDb> getCriteriaBuilderEndereco(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(EnderecoDb.class, context, valueParser);
    }

    @Bean
    DynamicCriteriaBuilder<EspecialidadeDb> getCriteriaBuilderEspecialidade(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(EspecialidadeDb.class, context, valueParser);
    }

    @Bean
    DynamicCriteriaBuilder<EstabelecimentoDbEntity> getCriteriaBuilderEstabelecimento(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(EstabelecimentoDbEntity.class, context, valueParser);
    }

    @Bean
    DynamicCriteriaBuilder<FuncionamentoDb> getCriteriaBuilderFuncionamento(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(FuncionamentoDb.class, context, valueParser);
    }

    @Bean
    DynamicCriteriaBuilder<PeriodoFuncionamentoDb> getCriteriaBuilderPeriodoFuncionamento(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(PeriodoFuncionamentoDb.class, context, valueParser);
    }

    @Bean
    DynamicCriteriaBuilder<ProfissionalDbEntity> getCriteriaBuilderProfissional(
            CriteriaContext context,
            ValueParser valueParser
    ) {
        return new DynamicCriteriaBuilder<>(ProfissionalDbEntity.class, context, valueParser);
    }

}
