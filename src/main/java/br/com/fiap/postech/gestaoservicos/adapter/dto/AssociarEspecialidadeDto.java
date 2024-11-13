package br.com.fiap.postech.gestaoservicos.adapter.dto;

import java.util.UUID;

public record AssociarEspecialidadeDto(
        UUID idEspecialidade,
        UUID idProfissional
) {
}
