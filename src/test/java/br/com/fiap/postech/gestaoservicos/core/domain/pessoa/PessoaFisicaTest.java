package br.com.fiap.postech.gestaoservicos.core.domain.pessoa;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CPF;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CPFTest;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PessoaFisicaTest {

    @Test
    public void deveCriarPessoaFisica_1() {
        UUID id = UUID.randomUUID();
        String nome = "João Paulo da Silva";
        String email = "joao.silva@exemplo.com";
        String numeroDocumento = "12312312312";
        LocalDate dataNascimento = LocalDate.of(1995, 4,13);

        PessoaFisica pessoaFisica = PessoaFisica.criar(
                id,
                nome,
                email,
                numeroDocumento,
                dataNascimento
        );

        assertThat(pessoaFisica.getId()).isEqualTo(id);
        assertThat(pessoaFisica.getNome()).isEqualTo(nome);
        assertThat(pessoaFisica.getDocumento().getNumeroDocumento()).isEqualTo(numeroDocumento);
        assertThat(pessoaFisica.getDocumento().getTipoDocumento()).isEqualTo(TipoDocumento.CPF);
        assertThat(pessoaFisica.getDataNascimento()).isEqualTo(dataNascimento);
    }

    @Test
    public void deveCriarPessoaFisica_2() {

        UUID id = UUID.randomUUID();
        String nome = "João Paulo da Silva";
        String email = "joao.silva@exemplo.com";
        CPF cpf = new CPF("12312312312");
        LocalDate dataNascimento = LocalDate.of(1995, 4,13);
        PessoaFisica pessoaFisica = PessoaFisica.criar(
                id,
                nome,
                email,
                cpf,
                dataNascimento
        );

        assertThat(pessoaFisica.getId()).isEqualTo(id);
        assertThat(pessoaFisica.getNome()).isEqualTo(nome);
        assertThat(pessoaFisica.getEmail()).isEqualTo(email);
        assertThat(pessoaFisica.getDocumento().getNumeroDocumento()).isEqualTo(cpf.getNumeroDocumento());
        assertThat(pessoaFisica.getDocumento().getTipoDocumento()).isEqualTo(TipoDocumento.CPF);
        assertThat(pessoaFisica.getDataNascimento()).isEqualTo(dataNascimento);
    }

    @Test
    public void deveCriarPessoaFisica_3() {

        String nome = "João Paulo da Silva";
        String email = "joao.silva@exemplo.com";
        String numeroDocumento = "12312312312";
        LocalDate dataNascimento = LocalDate.of(1995, 4,13);

        PessoaFisica pessoaFisica = PessoaFisica.criar(
                nome,
                email,
                numeroDocumento,
                dataNascimento
        );

        assertThat(pessoaFisica.getNome()).isEqualTo(nome);
        assertThat(pessoaFisica.getEmail()).isEqualTo(email);
        assertThat(pessoaFisica.getDocumento().getNumeroDocumento()).isEqualTo(numeroDocumento);
        assertThat(pessoaFisica.getDocumento().getTipoDocumento()).isEqualTo(TipoDocumento.CPF);
        assertThat(pessoaFisica.getDataNascimento()).isEqualTo(dataNascimento);
    }

    @Test
    public void deveCriarPessoaFisica_4() {

        String nome = "João Paulo da Silva";
        String email = "joao.silva@exemplo.com";
        CPF cpf = new CPF("12312312312");
        LocalDate dataNascimento = LocalDate.of(1995, 4,13);
        PessoaFisica pessoaFisica = PessoaFisica.criar(
                nome,
                email,
                cpf,
                dataNascimento
        );

        assertThat(pessoaFisica.getNome()).isEqualTo(nome);
        assertThat(pessoaFisica.getEmail()).isEqualTo(email);
        assertThat(pessoaFisica.getDocumento().getNumeroDocumento()).isEqualTo(cpf.getNumeroDocumento());
        assertThat(pessoaFisica.getDocumento().getTipoDocumento()).isEqualTo(TipoDocumento.CPF);
        assertThat(pessoaFisica.getDataNascimento()).isEqualTo(dataNascimento);
    }

}
