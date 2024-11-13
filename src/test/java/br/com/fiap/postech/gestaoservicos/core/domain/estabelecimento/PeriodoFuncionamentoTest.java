package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.PeriodoFuncionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoFinalNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoInicialNaoPodeSerNuloException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PeriodoFuncionamentoTest {

    @Test
    void deveCriarPeriodoFuncionamento() {
        LocalTime horaInicial = LocalTime.of(8, 0);
        LocalTime horaFinal = LocalTime.of(12, 0);

        PeriodoFuncionamento periodoFuncionamento = new PeriodoFuncionamento(
                horaInicial,
                horaFinal
        );

        assertThat(periodoFuncionamento.getHoraInicial()).isNotNull();
        assertThat(periodoFuncionamento.getHoraFinal()).isNotNull();
        assertThat(periodoFuncionamento).isNotNull();
    }

    @Test
    void deveCriarPeriodoFuncionamento_geraExcecaoSeHorarioInicialNulo() {
        assertThatThrownBy(()-> {
            LocalTime horaInicial = null;
            LocalTime horaFinal = LocalTime.of(12, 0);

            PeriodoFuncionamento periodoFuncionamento = new PeriodoFuncionamento(
                    horaInicial,
                    horaFinal
            );
        }).isInstanceOf(HorarioFuncionamentoInicialNaoPodeSerNuloException.class);

    }

    @Test
    void deveCriarPeriodoFuncionamento_geraExcecaoSeHorarioFinalNulo() {
        assertThatThrownBy(()-> {
            LocalTime horaInicial = LocalTime.of(8, 0);
            LocalTime horaFinal = null;

            PeriodoFuncionamento periodoFuncionamento = new PeriodoFuncionamento(
                    horaInicial,
                    horaFinal
            );
        }).isInstanceOf(HorarioFuncionamentoFinalNaoPodeSerNuloException.class);
    }

    @Test
    void deveCriarPeriodoFuncionamento_geraExcecaoSeHorarioFinalMenorQueInicial() {
        assertThatThrownBy(()-> {
            LocalTime horaInicial = LocalTime.of(12, 0);
            LocalTime horaFinal = LocalTime.of(8, 0);

            PeriodoFuncionamento periodoFuncionamento = new PeriodoFuncionamento(
                    horaInicial,
                    horaFinal
            );
        }).isInstanceOf(HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException.class);
    }
}
