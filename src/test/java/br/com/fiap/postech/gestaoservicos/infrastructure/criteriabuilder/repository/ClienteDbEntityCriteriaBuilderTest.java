package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.ClienteDbRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ClienteDbEntityCriteriaBuilderTest {

    @Autowired
    private DynamicCriteriaBuilder<ClienteDbEntity> criteriaBuilder;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {


//        Class<?> clazz = criteriaBuilder.getClazz();

//        criteriaBuilder.read();

        String spec = String.format(
                "nome=eq=%s", "João Souza");

        HashMap<String, Class<?>> fields = this.criteriaBuilder.classMap.getFields();

        for (String key : fields.keySet()) {
            System.out.println(key + " - " + fields.get(key));
        }

        Query q = this.criteriaBuilder.build(spec);

        List<ClienteDbEntity> cliente = this.mongoTemplate.find(q, ClienteDbEntity.class);

        System.out.println(q.toString());

    }
}
