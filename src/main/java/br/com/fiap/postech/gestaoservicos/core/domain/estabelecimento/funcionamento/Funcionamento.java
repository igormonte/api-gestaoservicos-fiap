package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.DiaSemana;
import lombok.Data;

import java.util.List;

@Data
public class Funcionamento {

    private DiaSemana diaSemana;
    private List<PeriodoFuncionamento> periodoFuncionamento;

    public Funcionamento(DiaSemana diaSemana,
                         List<PeriodoFuncionamento> periodoFuncionamento) {
        this.diaSemana = diaSemana;
        this.periodoFuncionamento = periodoFuncionamento;
    }

    public List<String> getPeriodoAsStringList() {
        return periodoFuncionamento.stream().map(
                p-> String.format("%s - %s", p.getHoraInicial().toString(), p.getHoraFinal().toString())
        ).toList();


    }


}