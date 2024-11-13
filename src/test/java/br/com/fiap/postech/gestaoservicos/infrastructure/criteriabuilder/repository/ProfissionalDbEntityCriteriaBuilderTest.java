package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class ProfissionalDbEntityCriteriaBuilderTest {

    @Autowired
    private DynamicCriteriaBuilder<ProfissionalDbEntity> criteriaBuilder;

    @Test
    void contextLoads() {


//        Class<?> clazz = criteriaBuilder.getClazz();

//        criteriaBuilder.read();

        HashMap<String, Class<?>> fields = this.criteriaBuilder.classMap.getFields();

        for (String key : fields.keySet()) {
            System.out.println(key + " - " + fields.get(key));
        }

    }
}
