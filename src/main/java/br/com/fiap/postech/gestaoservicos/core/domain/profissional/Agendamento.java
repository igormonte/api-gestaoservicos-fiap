package br.com.fiap.postech.gestaoservicos.core.domain.profissional;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class Agendamento {

    private UUID id;
    private LocalDateTime dataHora;
    private LocalTime duracao;
    private ServicoEntity servico;

    public Agendamento() {}

    public Agendamento(
            LocalDateTime dataHora,
            ServicoEntity servico) {
        this.dataHora = dataHora;
        this.servico = servico;
    }

    public static Agendamento criaAgendamento(
            LocalDateTime dataHora,
            EstabelecimentoEntity estabelecimento,
            ClienteEntity cliente,
            ProfissionalEntity profissional,
            Especialidade especialidade) {

        ServicoEntity servico = new ServicoEntity(
                estabelecimento, profissional, cliente, especialidade
        );

        return new Agendamento(
                dataHora,
                servico

        );

    }

}
