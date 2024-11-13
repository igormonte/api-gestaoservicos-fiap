package br.com.fiap.postech.gestaoservicos.infrastructure.mapper;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EspecialidadeDb;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface EspecialidadeMapper {

    Especialidade toEspecialidade(EspecialidadeDb especialidadeDb);

    EspecialidadeDb toEspecialidadeDb(Especialidade especialidade);

    List<Especialidade> toEspecialidadeList(List<EspecialidadeDb> especialidadeDbList);
}
