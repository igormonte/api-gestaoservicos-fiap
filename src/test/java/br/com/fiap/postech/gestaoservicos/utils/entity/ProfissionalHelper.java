package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.EspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.ProfissionalDto;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ProfissionalHelper {
    public static ProfissionalEntity getProfissional() {
        List<Especialidade> especialidadeList = new LinkedList<>();
        especialidadeList.add(EspecialidadeHelper.getEspecialidade());
        return new ProfissionalEntity(
                PessoaJuridicaHelper.getPessoaJuridica(),
                null,
                null
        );
    }
    public static ProfissionalDto getProfissionalDto() {
        List<EspecialidadeDto> especialidadeList = new LinkedList<>();
        especialidadeList.add(EspecialidadeHelper.getEspecialidadeDto());
        return new ProfissionalDto(
                UUID.randomUUID(),
                "João da Silva ME",
                "joao.silva@exemplo.com",
                LocalDate.of(1990, 5, 15),
                TipoDocumento.CNPJ,
                "12123123000112",
                especialidadeList,
                null
        );
    }


    public static ProfissionalDto getProfissionalDto(ProfissionalEntity profissional) {
        List<EspecialidadeDto> especialidadeList = new LinkedList<>();
        especialidadeList.add(EspecialidadeHelper.getEspecialidadeDto());
        return new ProfissionalDto(
                profissional.getId(),
                profissional.getPessoa().getNome(),
                profissional.getPessoa().getEmail(),
                profissional.getPessoa().getDataNascimento(),
                profissional.getPessoa().getDocumento().getTipoDocumento(),
                profissional.getPessoa().getDocumento().getNumeroDocumento(),
                profissional.getEspecialidade().stream().map(EspecialidadeHelper::getEspecialidadeDto).toList(),
                null
        );
    }
}
