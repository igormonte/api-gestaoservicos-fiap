package br.com.fiap.postech.gestaoservicos.core.domain.profissional;

import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.NomeEspecialidadeNaoPodeSerNuloException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class EspecialidadeTest {

    @Test
    void deveCriarEspecialidade() {

        String nome = "Corte";
        String descricao = "Corte de cabelo";
        LocalTime duracao = LocalTime.of(1, 0);

        Especialidade especialidade = new Especialidade(
                nome,
                descricao,
                duracao
        );

        assertThat(especialidade.getNome()).isEqualTo(nome);
        assertThat(especialidade.getDescricao()).isEqualTo(descricao);
        assertThat(especialidade.getDuracao()).isEqualTo(duracao);

    }

}
