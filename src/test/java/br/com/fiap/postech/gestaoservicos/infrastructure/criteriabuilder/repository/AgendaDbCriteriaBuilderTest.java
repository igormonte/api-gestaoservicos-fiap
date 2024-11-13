package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendaDb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
public class AgendaDbCriteriaBuilderTest {

    @Autowired
    private DynamicCriteriaBuilder<AgendaDb> criteriaBuilder;

    @Test
    void contextLoads() {


//        Class<?> clazz = criteriaBuilder.getClazz();

//        criteriaBuilder.read();

        String spec = String.format(
                "agendamento.id=eq=%s&&agendamento.dataHora=eq=2024-10-29T02:00:00", UUID.randomUUID());

        HashMap<String, Class<?>> fields = this.criteriaBuilder.classMap.getFields();

        for (String key : fields.keySet()) {
            System.out.println(key + " - " + fields.get(key));
        }

        Query q = this.criteriaBuilder.build(spec);

        System.out.println(q.toString());

    }
}
