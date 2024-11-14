package br.com.fiap.postech.gestaoservicos.core.domain.profissional;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.NomeInvalidoException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaFisica;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.EmailNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.NomeNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.NumeroDoDocumentoNaoPodeSerNuloException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class ProfissionalEntityTest {

    @Test
    void deveCriarProfissional_1() {
        String nome = "João Paulo Nascimento";
        String email = "joao.nascimento@exemplo.com";
        String numeroDocumento = "12345678912";
        LocalDate data = LocalDate.of(1990, 1, 1);
        PessoaFisica pessoaFisica = PessoaFisica.criar(
                nome, email, numeroDocumento, data
        );

        ProfissionalEntity profissional = new ProfissionalEntity(
                pessoaFisica, null, null
        );

        assertThat(profissional.getPessoa().getNome()).isEqualTo(nome);
        assertThat(profissional.getPessoa().getEmail()).isEqualTo(email);
        assertThat(profissional.getPessoa().getDocumento().getNumeroDocumento()).isEqualTo(numeroDocumento);
        assertThat(profissional.getPessoa().getDataNascimento()).isEqualTo(data);
    }

    @Test
    void deveCriarProfissional_2() {
        String nome = "João Paulo Nascimento";
        String email = "joao.nascimento@exemplo.com";
        String numeroDocumento = "12345678912";
        LocalDate data = LocalDate.of(1990, 1, 1);
        PessoaFisica pessoaFisica = PessoaFisica.criar(
                nome, email, numeroDocumento, data
        );

        ProfissionalEntity profissional = new ProfissionalEntity(
                pessoaFisica
        );

        assertThat(profissional.getPessoa().getNome()).isEqualTo(nome);
        assertThat(profissional.getPessoa().getEmail()).isEqualTo(email);
        assertThat(profissional.getPessoa().getDocumento().getNumeroDocumento()).isEqualTo(numeroDocumento);
        assertThat(profissional.getPessoa().getDataNascimento()).isEqualTo(data);
    }

    @Test
    void deveCriarProfissional_GeraExcessaoSeNomeNulo(){

        String nome = null;
        String email = "joao.nascimento@exemplo.com";
        String numeroDocumento = "12345678912";
        LocalDate data = LocalDate.of(1990, 1, 1);

        assertThatThrownBy(()-> PessoaFisica.criar(
                nome, email, numeroDocumento, data)
        ).isInstanceOf(NomeNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarProfissional_GeraExcessaoSeEmailNulo(){

        String nome = "João Paulo Nascimento";
        String email = null;
        String numeroDocumento = "12345678912";
        LocalDate data = LocalDate.of(1990, 1, 1);

        assertThatThrownBy(()-> PessoaFisica.criar(
                nome, email, numeroDocumento, data)
        ).isInstanceOf(EmailNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarProfissional_GeraExcessaoSeNumeroDocumentoNulo(){


        String nome = "João Paulo Nascimento";
        String email = "joao.nascimento@exemplo.com";
        String numeroDocumento = null;
        LocalDate data = LocalDate.of(1990, 1, 1);

        assertThatThrownBy(()-> PessoaFisica.criar(
                nome, email, numeroDocumento, data)
        ).isInstanceOf(NumeroDoDocumentoNaoPodeSerNuloException.class);
    }


}
