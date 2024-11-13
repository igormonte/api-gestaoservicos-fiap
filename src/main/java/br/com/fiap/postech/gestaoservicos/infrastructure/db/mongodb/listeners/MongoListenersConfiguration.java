package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.listeners;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MongoListenersConfiguration {
    @Bean
    public ClienteDbEntityEventListener getClienteDbEntityEventListener() {
        return new ClienteDbEntityEventListener();
    }

    @Bean
    public AgendamentoDbEventListener getAgendamentoDbEventListener() {
        return new AgendamentoDbEventListener();
    }

    @Bean
    public ProfissionalDbEntityEventListener getProfissionalDbEntityEventListener() {
        return new ProfissionalDbEntityEventListener();
    }

    @Bean
    EspecialidadeDbEventListener getEspecialidadeDbEventListener() {
        return new EspecialidadeDbEventListener();
    }

    @Bean
    EstabelecimentoDbEventListener getEstabelecimentoDbEventListener() {
        return new EstabelecimentoDbEventListener();
    }
}
