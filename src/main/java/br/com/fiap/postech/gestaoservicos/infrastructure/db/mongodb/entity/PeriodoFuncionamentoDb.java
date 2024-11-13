package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoFinalNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoInicialNaoPodeSerMaiorQueFinalException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.exception.HorarioFuncionamentoInicialNaoPodeSerNuloException;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class PeriodoFuncionamentoDb {

    @Id
    private UUID id;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

}
