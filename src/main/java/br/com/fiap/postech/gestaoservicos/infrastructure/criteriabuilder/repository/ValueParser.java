package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.repository;

import java.lang.reflect.Field;

public interface ValueParser {
    Object parseValue(String value, Class<?> supplier);

}
