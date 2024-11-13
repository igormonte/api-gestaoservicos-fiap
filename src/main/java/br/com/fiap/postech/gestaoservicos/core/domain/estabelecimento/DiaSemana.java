package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import java.time.DayOfWeek;

public enum DiaSemana {
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO,
    DOMINGO;

    public static DiaSemana deDayOfWeek(DayOfWeek dayOfWeek) {
        return DiaSemana.values()[dayOfWeek.getValue()-1];

    }
}
