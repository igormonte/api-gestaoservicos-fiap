package br.com.fiap.postech.gestaoservicos.core.domain.pessoa;

import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CNPJTest;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.CNPJ;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PessoaJuridicaTest {

    @Test
    public void deveCriarPessoaJuridica_1() {
        UUID id = UUID.randomUUID();
        String nome = "João Paulo da Silva";
        String email = "joao.silva@exemplo.com";
        String numeroDocumento = "12312312312";
        LocalDate dataNascimento = LocalDate.of(1995, 4,13);

        PessoaJuridica pessoaJuridica = PessoaJuridica.criar(
                nome,
                email,
                numeroDocumento,
                dataNascimento
        );

        assertThat(pessoaJuridica.getNome()).isEqualTo(nome);
        assertThat(pessoaJuridica.getEmail()).isEqualTo(email);
        assertThat(pessoaJuridica.getDocumento().getNumeroDocumento()).isEqualTo(numeroDocumento);
        assertThat(pessoaJuridica.getDocumento().getTipoDocumento()).isEqualTo(TipoDocumento.CNPJ);
        assertThat(pessoaJuridica.getDataNascimento()).isEqualTo(dataNascimento);
    }

    @Test
    public void deveCriarPessoaJuridica_2() {

        UUID id = UUID.randomUUID();
        String nome = "João Paulo da Silva";
        String email = "joao.silva@exemplo.com";
        CNPJ cnpj = new CNPJ("12312312312");
        LocalDate dataNascimento = LocalDate.of(1995, 4,13);
        PessoaJuridica pessoaJuridica = PessoaJuridica.criar(
                id,
                nome,
                email,
                cnpj,
                dataNascimento
        );

        assertThat(pessoaJuridica.getId()).isEqualTo(id);
        assertThat(pessoaJuridica.getNome()).isEqualTo(nome);
        assertThat(pessoaJuridica.getEmail()).isEqualTo(email);
        assertThat(pessoaJuridica.getDocumento().getNumeroDocumento()).isEqualTo(cnpj.getNumeroDocumento());
        assertThat(pessoaJuridica.getDocumento().getTipoDocumento()).isEqualTo(TipoDocumento.CNPJ);
        assertThat(pessoaJuridica.getDataNascimento()).isEqualTo(dataNascimento);
    }

}
