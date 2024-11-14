package br.com.fiap.postech.gestaoservicos.core.domain.pessoa;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CPF;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.Documento;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.DocumentoTest;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.DataNascimentoNaoPodeSerNulaException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.DocumentoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.EmailNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.exception.NomeNaoPodeSerNuloException;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

@Data
public class PessoaTest {

    @Test
    void deveCriarPessoa() {

        String nome = "João da Silva";
        String email = "joao.silva@exemplo.com";
        Documento documento = new CPF("123.456.789-00");
        LocalDate dataNascimento = LocalDate.of(1990, 5, 15);
        Pessoa pessoa = new Pessoa(
                nome,
                email,
                documento,
                dataNascimento
        );
        assertThat(pessoa)
                .hasFieldOrPropertyWithValue("nome", nome)
                .hasFieldOrPropertyWithValue("documento", documento)
                .hasFieldOrPropertyWithValue("dataNascimento", dataNascimento);
    }

    @Test
    void deveCriarPessoa_GeraExcessaoSeNomeNulo() {
        assertThatThrownBy(() -> new Pessoa(
                null,
                "joao.silva@exemplo.com",
                new CPF("123.456.789-00"),
                LocalDate.of(1990, 5, 15)
        )).isInstanceOf(NomeNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarPessoa_GeraExcessaoSeEmailNulo() {
        assertThatThrownBy(() -> new Pessoa(
                "João da Silva",
                null,
                new CPF("123.456.789-00"),
                LocalDate.of(1990, 5, 15)
        )).isInstanceOf(EmailNaoPodeSerNuloException.class);
    }


    @Test
    void deveCriarPessoa_GeraExcessaoSeDocumentoNulo() {
        assertThatThrownBy(() -> new Pessoa(
                "João da Silva",
                "joao.silva@exemplo.com",
                null,
                LocalDate.of(1990, 5, 15)
        )).isInstanceOf(DocumentoNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarPessoa_GeraExcessaoSeDataNascimentoNulo() {
        assertThatThrownBy(() -> new Pessoa(
                "João da Silva",
                "joao.silva@exemplo.com",
                new CPF("123.456.789-00"),
                null
        )).isInstanceOf(DataNascimentoNaoPodeSerNulaException.class);
    }
}
