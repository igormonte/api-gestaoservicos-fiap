package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Endereco;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Foto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.EstabelecimentoRepository;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ProfissionalRepository;

import java.util.List;
import java.util.UUID;

public class CadastrarEstabelecimentoUseCaseImpl implements CadastrarEstabelecimentoUseCase {
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProfissionalRepository profissionalRepository;

    public CadastrarEstabelecimentoUseCaseImpl(
            EstabelecimentoRepository estabelecimentoRepository,
            ProfissionalRepository profissionalRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.profissionalRepository = profissionalRepository;
    }
    @Override
    public EstabelecimentoEntity execute(
            String nome,
            Endereco endereco,
            List<UUID> idProfissional,
            List<Funcionamento> funcionamento,
            List<String> foto) {

        List<ProfissionalEntity> profissionalList= null;
        List<Foto> fotoList = null;

        if (idProfissional != null) {
            profissionalList = idProfissional.stream().map(this.profissionalRepository::buscarPorId).toList();
        }

        if(foto != null) {
            fotoList = foto.stream().map(Foto::new).toList();

        }

        EstabelecimentoEntity estabelecimento = new EstabelecimentoEntity(
            nome,
            endereco,
            profissionalList,
            funcionamento,
            fotoList
        );

        return this.estabelecimentoRepository.criarEstabelecimento(estabelecimento);
    }
}
