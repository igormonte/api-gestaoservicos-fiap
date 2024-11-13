package br.com.fiap.postech.gestaoservicos.infrastructure.criteriabuilder.data;

import lombok.Data;

import java.util.HashMap;

@Data
public class ClassMap {

    private Class<?> clazz;
    private HashMap<String, Class<?>> fields;

    public ClassMap(Class<?> clazz) {
        this.clazz = clazz;
        this.fields = new HashMap<>();
    }

    public void addField(String name, Class<?> field) {
        this.fields.put(name, field);
    }


    public Class<?> getField(String name) {
        return this.fields.get(name);
    }

    public Boolean hasField(String name) {
        return this.fields.containsKey(name);
    }
    public Boolean hasFieldPart(String name) {
        return this.fields.keySet().stream().anyMatch(f-> f.contains(name));
    }

    public Boolean hasClass(Class<?> clazz) {
        return this.fields.values().stream()
                .anyMatch(c-> c.getName().equals(clazz.getName()));
    }

}
