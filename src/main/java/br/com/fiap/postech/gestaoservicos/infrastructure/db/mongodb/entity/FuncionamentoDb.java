package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.DiaSemana;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class FuncionamentoDb {

    @Id
    private UUID id;
    private DiaSemana diaSemana;
    private List<PeriodoFuncionamentoDb> periodoFuncionamento;

}