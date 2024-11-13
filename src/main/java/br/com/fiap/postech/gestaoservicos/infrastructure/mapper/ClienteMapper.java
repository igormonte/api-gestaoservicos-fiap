package br.com.fiap.postech.gestaoservicos.infrastructure.mapper;


import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface ClienteMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "pessoa.nome")
    @Mapping(target = "email", source = "pessoa.email")
    @Mapping(target = "dataNascimento", source = "pessoa.dataNascimento")
    @Mapping(target = "tipoDocumento", source = "pessoa.documento.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "pessoa.documento.numeroDocumento")
    ClienteDbEntity toClienteDbEntity(ClienteEntity clienteEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "pessoa.id", source = "id")
    @Mapping(target = "pessoa.nome", source = "nome")
    @Mapping(target = "pessoa.email", source = "email")
    @Mapping(target = "pessoa.dataNascimento", source = "dataNascimento")
    @Mapping(target = "pessoa.documento.tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "pessoa.documento.numeroDocumento", source = "numeroDocumento")
    ClienteEntity toClienteEntity(ClienteDbEntity clienteDbEntity);

    List<ClienteEntity> toClienteEntityList(List<ClienteDbEntity> all);
}
