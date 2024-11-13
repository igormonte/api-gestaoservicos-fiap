package br.com.fiap.postech.gestaoservicos.utils.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper()
                    .findAndRegisterModules()
                    .writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
