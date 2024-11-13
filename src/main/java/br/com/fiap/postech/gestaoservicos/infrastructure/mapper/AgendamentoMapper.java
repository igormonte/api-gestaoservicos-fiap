package br.com.fiap.postech.gestaoservicos.infrastructure.mapper;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Foto;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Agendamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.AgendamentoDb;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EstabelecimentoDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface AgendamentoMapper {

    @Mapping(target = "servico.profissional", source = "servico.profissional", qualifiedByName = "toProfissionalDbEntity")
    @Mapping(target = "servico.cliente", source = "servico.cliente", qualifiedByName = "toClienteDbEntity")
    @Mapping(target = "servico.estabelecimento", source = "servico.estabelecimento", qualifiedByName = "toEstabelecimentoDbEntity")
    AgendamentoDb toAgendamentoDb(Agendamento agendamento);

    @Mapping(target = "servico.profissional", source = "servico.profissional", qualifiedByName = "toProfissionalEntity")
    @Mapping(target = "servico.cliente", source = "servico.cliente", qualifiedByName = "toClienteEntity")
    @Mapping(target = "servico.estabelecimento", source = "servico.estabelecimento", qualifiedByName = "toEstabelecimentoEntity")
    Agendamento toAgendamento(AgendamentoDb agendamentoDb);
    List<Agendamento> toAgendamentoList(List<AgendamentoDb> agendamentoDbList);


    @Named("toClienteDbEntity")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "pessoa.nome")
    @Mapping(target = "email", source = "pessoa.email")
    @Mapping(target = "dataNascimento", source = "pessoa.dataNascimento")
    @Mapping(target = "tipoDocumento", source = "pessoa.documento.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "pessoa.documento.numeroDocumento")
    ClienteDbEntity toClienteDbEntity(ClienteEntity clienteEntity);

    @Named("toClienteEntity")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "pessoa.id", source = "id")
    @Mapping(target = "pessoa.nome", source = "nome")
    @Mapping(target = "pessoa.email", source = "email")
    @Mapping(target = "pessoa.dataNascimento", source = "dataNascimento")
    @Mapping(target = "pessoa.documento.tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "pessoa.documento.numeroDocumento", source = "numeroDocumento")
    ClienteEntity toClienteEntity(ClienteDbEntity clienteDbEntity);
    @Named("toProfissionalDbEntity")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "pessoa.nome")
    @Mapping(target = "email", source = "pessoa.email")
    @Mapping(target = "dataNascimento", source = "pessoa.dataNascimento")
    @Mapping(target = "tipoDocumento", source = "pessoa.documento.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "pessoa.documento.numeroDocumento")
    ProfissionalDbEntity toProfissionalDbEntity(ProfissionalEntity profissionalEntity);

    @Named("toProfissionalEntity")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "pessoa.id", source = "id")
    @Mapping(target = "pessoa.nome", source = "nome")
    @Mapping(target = "pessoa.email", source = "email")
    @Mapping(target = "pessoa.dataNascimento", source = "dataNascimento")
    @Mapping(target = "pessoa.documento.tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "pessoa.documento.numeroDocumento", source = "numeroDocumento")
    ProfissionalEntity toProfissionalEntity(ProfissionalDbEntity profissionalDbEntity);

    @Named("toEstabelecimentoEntity")
    @Mapping(target = "profissional", source = "profissional", qualifiedByName = "toProfissionalEntity")
    EstabelecimentoEntity toEstabelecimentoEntity(EstabelecimentoDbEntity estabelecimentoDbEntity);

    @Named("toEstabelecimentoDbEntity")
    @Mapping(target = "profissional", source = "profissional", qualifiedByName = "toProfissionalDbEntity")
    EstabelecimentoDbEntity toEstabelecimentoDbEntity(EstabelecimentoEntity estabelecimentoEntity);

    default List<Foto> toFotoList(List<String> stringList) {
        return stringList.stream().map(this::toFoto).toList();
    }

    default List<String> toStringList(List<Foto> fotoList) {
        return fotoList.stream().map(this::toString).toList();
    }

    default Foto toFoto(String string) {
        return new Foto(string);
    }

    default String toString(Foto foto) {
        return foto.getUrl();
    }

}
