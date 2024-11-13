package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CadastrarPerfilProfissionalUseCase {

    ProfissionalEntity executar(
            String nome,
            String email,
            String numeroDocumento,
            TipoDocumento tipoDocumento,
            LocalDate dataNascimento,
            List<UUID> idEspecialidadeList);
}
