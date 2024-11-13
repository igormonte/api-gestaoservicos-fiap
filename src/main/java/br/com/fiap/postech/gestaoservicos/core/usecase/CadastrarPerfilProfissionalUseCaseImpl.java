package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaFisica;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaJuridica;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EspecialidadeRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CadastrarPerfilProfissionalUseCaseImpl implements CadastrarPerfilProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;

    private final EspecialidadeRepository especialidadeRepository;

    public CadastrarPerfilProfissionalUseCaseImpl(
            ProfissionalRepository profissionalRepository,
            EspecialidadeRepository especialidadeRepository) {
        this.profissionalRepository = profissionalRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public ProfissionalEntity executar(
            String nome,
            String email,
            String numeroDocumento,
            TipoDocumento tipoDocumento,
            LocalDate dataNascimento,
            List<UUID> idEspecialidadeList) {

        List<Especialidade> especialidadeList =
                this.especialidadeRepository.buscarEspecialidadesPorIds(idEspecialidadeList);

        Pessoa pessoa = switch(tipoDocumento) {
            case CPF -> PessoaFisica.criar(nome, email, numeroDocumento, dataNascimento);
            case CNPJ -> PessoaJuridica.criar(nome, email, numeroDocumento, dataNascimento);
        };

        ProfissionalEntity profissional = new ProfissionalEntity(pessoa);
        profissional.setEspecialidade(especialidadeList);

        return this.profissionalRepository.salvarProfissional(profissional);
    }
}
