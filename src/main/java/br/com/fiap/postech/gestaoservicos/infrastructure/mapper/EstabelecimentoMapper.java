package br.com.fiap.postech.gestaoservicos.infrastructure.mapper;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.CadastrarEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.ProfissionalDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento.ResponseEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.response.estabelecimento.ResponseProfissionalDto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Foto;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.EstabelecimentoDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface EstabelecimentoMapper {

    @Mapping(target = "profissional", source = "profissional", qualifiedByName = "toResponseProfissionalDto")
    ResponseEstabelecimentoDto toResponseEstabelecimentoDto(EstabelecimentoEntity estabelecimento);

    @Mapping(target = "profissional", source = "profissional", qualifiedByName = "toProfissionalEntity")
    EstabelecimentoEntity toEstabelecimentoEntity(EstabelecimentoDbEntity estabelecimentoDbEntity);

    @Mapping(target = "profissional", source = "profissional", qualifiedByName = "dtoToProfissionalEntity")
    EstabelecimentoEntity  toEstabelecimentoEntity(CadastrarEstabelecimentoDto cadastrarEstabelecimentoDto);

    @Mapping(target = "profissional", source = "profissional", qualifiedByName = "toProfissionalDbEntity")
    EstabelecimentoDbEntity toEstabelecimentoDbEntity(EstabelecimentoEntity estabelecimentoEntity);

    @Named("toResponseProfissionalDto")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "pessoa.nome")
    @Mapping(target = "email", source = "pessoa.email")
    @Mapping(target = "dataNascimento", source = "pessoa.dataNascimento")
    @Mapping(target = "tipoDocumento", source = "pessoa.documento.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "pessoa.documento.numeroDocumento")
    ResponseProfissionalDto toResponseProfissionalDto(ProfissionalEntity profissionalEntity);

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

    @Named("dtoToProfissionalEntity")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "pessoa.id", source = "id")
    @Mapping(target = "pessoa.nome", source = "nome")
    @Mapping(target = "pessoa.email", source = "email")
    @Mapping(target = "pessoa.dataNascimento", source = "dataNascimento")
    @Mapping(target = "pessoa.documento.tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "pessoa.documento.numeroDocumento", source = "numeroDocumento")
    ProfissionalEntity toProfissionalEntity(ProfissionalDto profissionalDto);

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

    List<EstabelecimentoEntity> toEstabelecimentoEntityList(List<EstabelecimentoDbEntity> estabelecimentoDbEntityList);

    List<ResponseEstabelecimentoDto> toResponseEstabelecimentoDtoList(List<EstabelecimentoEntity> estabelecimentoEntityList);

}
