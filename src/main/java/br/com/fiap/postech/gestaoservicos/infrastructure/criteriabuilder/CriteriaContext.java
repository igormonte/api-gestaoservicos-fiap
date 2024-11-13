package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder;

import lombok.Data;
import org.springframework.context.ApplicationContext;

@Data
public class CriteriaContext {

    private String packageName;

    public CriteriaContext(String packageName) {
        this.packageName =  packageName;
    }

}
