package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EnderecoNaoPodeSerNuloException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class EnderecoTest {

    @Test
    void deveCriarEndereco() {
        String rua = "Rua Jamilton Nascimento";
        Integer numero = 300;
        String bairro = "Bairro do Juraci";
        String cidade = "Itapema";
        String estado = "SP";
        String cep = "000000";
        Endereco endereco = new Endereco(
                rua,
                numero,
                bairro,
                cidade,
                estado,
                cep
        );

        assertThat(endereco.getRua()).isEqualTo(rua);
        assertThat(endereco.getNumero()).isEqualTo(numero);
        assertThat(endereco.getBairro()).isEqualTo(bairro);
        assertThat(endereco.getCidade()).isEqualTo(cidade);
        assertThat(endereco.getEstado()).isEqualTo(estado);
        assertThat(endereco.getCep()).isEqualTo(cep);

    }

    @Test
    void deveCriarEndereco_GeraExcecaoSeRuaNula() {

        assertThatThrownBy(()->
            new Endereco(
                    null,
                    300,
                    "Bairro do Juraci",
                    "Itapema",
                    "SP",
                    "000000"
            )
        ).isInstanceOf(EnderecoNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarEndereco_GeraExcecaoSeNumeroNulo() {

        assertThatThrownBy(()->
            new Endereco(
                    "Rua Jamilton Nascimento",
                    null,
                    "Bairro do Juraci",
                    "Itapema",
                    "SP",
                    "000000"
            )
        ).isInstanceOf(EnderecoNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarEndereco_GeraExcecaoSeBairroNulo() {

        assertThatThrownBy(()->
            new Endereco(
                    "Rua Jamilton Nascimento",
                    300,
                    null,
                    "Itapema",
                    "SP",
                    "000000"

            )
        ).isInstanceOf(EnderecoNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarEndereco_GeraExcecaoSeCidadeNula() {

        assertThatThrownBy(()->
            new Endereco(
                    "Rua Jamilton Nascimento",
                    300,
                    "Bairro do Juraci",
                    null,
                    "SP",
                    "000000"
            )
        ).isInstanceOf(EnderecoNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarEndereco_GeraExcecaoSeEstadoNulo() {

        assertThatThrownBy(()->
            new Endereco (
                    "Rua Jamilton Nascimento",
                    300,
                    "Bairro do Juraci",
                    "Itapema",
                    null,
                    "000000"
            )
        ).isInstanceOf(EnderecoNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarEndereco_GeraExcecaoSeCepNulo() {

        assertThatThrownBy(()->
            new Endereco(
                    "Rua Jamilton Nascimento",
                    300,
                    "Bairro do Juraci",
                    "Itapema",
                    "SP",
                    null
            )
        ).isInstanceOf(EnderecoNaoPodeSerNuloException.class);
    }

}
