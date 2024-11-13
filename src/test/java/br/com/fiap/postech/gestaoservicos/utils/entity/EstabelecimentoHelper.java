package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento.CadastrarEstabelecimentoDto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Foto;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;

import java.util.LinkedList;
import java.util.List;

public class EstabelecimentoHelper {

    public static EstabelecimentoEntity getEstabelecimento() {

        List<ProfissionalEntity> profissionalEntityList = new LinkedList<>();
        profissionalEntityList.add(ProfissionalHelper.getProfissional());

        List<Funcionamento> funcionamento = FuncionamentoHelper.getFuncionamentoList();

        List<Foto> foto = new LinkedList<>();
        foto.add(FotoHelper.getFoto());

        return new EstabelecimentoEntity(
                "Estabelecimento Teste",
                EnderecoHelper.criarEndereco(),
                profissionalEntityList,
                funcionamento,
                foto
        );
    }

    public static CadastrarEstabelecimentoDto getCadastrarEstabelecimento() {
        return new CadastrarEstabelecimentoDto(
                "Estabelecimento Teste",
                EnderecoHelper.criarEnderecoDto(),
                List.of(ProfissionalHelper.getProfissionalDto()),
                List.of(FuncionamentoHelper.getFuncionamentoDto()),
                List.of("https://example.com/foto.jpg")
        );
    }

    public static CadastrarEstabelecimentoDto getCadastrarEstabelecimento(ProfissionalEntity profissional) {
        return new CadastrarEstabelecimentoDto(
                "Estabelecimento Teste",
                EnderecoHelper.criarEnderecoDto(),
                List.of(ProfissionalHelper.getProfissionalDto(profissional)),
                List.of(FuncionamentoHelper.getFuncionamentoDto()),
                List.of("https://example.com/foto.jpg")
        );
    }


}
