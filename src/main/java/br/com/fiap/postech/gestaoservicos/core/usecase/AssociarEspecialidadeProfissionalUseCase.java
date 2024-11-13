package br.com.fiap.postech.gestaoservicos.core.usecase;

import java.util.UUID;

public interface AssociarEspecialidadeProfissionalUseCase {

    Boolean executar(UUID idEspecialidade, UUID idProfissional);

}
