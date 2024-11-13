package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EnderecoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.NomeInvalidoException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class EstabelecimentoEntityTest {

    @Test
    void deveCriarEstabelecimento() {
        String nome = "Teste";
        Endereco endereco = new Endereco("Rua Exemplo", 1, "Bairro Exemplo", "São Paulo", "SP", "12312-123");
        List<ProfissionalEntity> profissional = new LinkedList<>();
        List<Funcionamento> funcionamento = new LinkedList<>();
        List<Foto> foto = new LinkedList<>();

        EstabelecimentoEntity estabelecimento = new EstabelecimentoEntity(
                nome,
                endereco,
                profissional,
                funcionamento,
                foto
        );

        assertThat(estabelecimento.getNome()).isEqualTo(nome);
        assertThat(estabelecimento.getEndereco()).isEqualTo(endereco);
        assertThat(estabelecimento.getProfissional()).isEqualTo(profissional);
        assertThat(estabelecimento.getFuncionamento()).isEqualTo(funcionamento);
        assertThat(estabelecimento.getFoto()).isEqualTo(foto);

    }

    @Test
    void deveCriarEstabelecimento_GeraExcessaoSeNomeInvalido() {
        String nome = "";
        Endereco endereco = new Endereco("Rua Exemplo", 1, "Bairro Exemplo", "São Paulo", "SP", "12312-123");
        List<ProfissionalEntity> profissional = new LinkedList<>();
        List<Funcionamento> funcionamento = new LinkedList<>();
        List<Foto> foto = new LinkedList<>();

        assertThatThrownBy(()->new EstabelecimentoEntity(
                nome,
                endereco,
                profissional,
                funcionamento,
                foto
        )).isInstanceOf(NomeInvalidoException.class);
    }

    @Test
    void deveCriarEstabelecimento_GeraExcessaoSeEnderecoNulo() {
        String nome = "Teste";
        Endereco endereco = null;
        List<ProfissionalEntity> profissional = new LinkedList<>();
        List<Funcionamento> funcionamento = new LinkedList<>();
        List<Foto> foto = new LinkedList<>();

        assertThatThrownBy(()->new EstabelecimentoEntity(
                nome,
                endereco,
                profissional,
                funcionamento,
                foto
        )).isInstanceOf(EnderecoNaoPodeSerNuloException.class);
    }

//    @Test
//    void deveCriarEstabelecimento_GeraExcessaoSe() {
//        fail("Teste não implementado");
//    }
}
