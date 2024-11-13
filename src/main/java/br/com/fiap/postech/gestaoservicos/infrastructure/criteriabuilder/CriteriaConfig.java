package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CriteriaConfig {

    @Bean
    public CriteriaContext getCriteriaContext(ApplicationContext context) {
        Map<String, Object> annotatedBeans =
                context.getBeansWithAnnotation(SpringBootApplication.class);

        return new CriteriaContext(
                annotatedBeans.isEmpty() ? null : annotatedBeans.values().toArray()[0].getClass().getPackageName());
    }


}
