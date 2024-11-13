package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.EspecialidadeDto;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;

import java.time.LocalTime;
import java.util.UUID;

public class EspecialidadeHelper {

    public static Especialidade getEspecialidade() {
        return new Especialidade("Barbeiro", "Cortes de Cabelo e Barba.", LocalTime.of(1, 0));
    }

    public static EspecialidadeDto getEspecialidadeDto() {
        return new EspecialidadeDto(UUID.randomUUID());
    }
    public static EspecialidadeDto getEspecialidadeDto(Especialidade especialidade) {
        return new EspecialidadeDto(especialidade.getId());
    }

}
