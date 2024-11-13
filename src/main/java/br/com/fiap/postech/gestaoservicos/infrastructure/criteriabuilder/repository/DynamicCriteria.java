package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository;

import org.springframework.data.mongodb.core.query.Query;

public interface DynamicCriteria<T> {

    Query build(String spec);

}
