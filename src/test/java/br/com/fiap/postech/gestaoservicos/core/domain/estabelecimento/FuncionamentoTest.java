package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.PeriodoFuncionamento;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class FuncionamentoTest {

    @Test
    void deveCriarFuncionamento() {
        DiaSemana diaSemana = DiaSemana.SEGUNDA;
        List<PeriodoFuncionamento> periodoFuncionamento =
                List.of(new PeriodoFuncionamento(LocalTime.of(8, 0), LocalTime.of(18, 0)));
        Funcionamento  funcionamento =
                new Funcionamento(diaSemana, periodoFuncionamento);
    }

//    @Test
//    void deveCriarFuncionamento_GeraExcessaoSe() {
//        fail("Teste não implementado");
//    }
}
