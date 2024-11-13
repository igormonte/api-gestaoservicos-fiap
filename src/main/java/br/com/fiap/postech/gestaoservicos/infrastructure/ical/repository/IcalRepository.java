package br.com.fiap.postech.gestaoservicos.infrastructure.ical.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto.IcalCalendarioDto;

public interface IcalRepository {
    public abstract byte[] gerarCalendario(IcalCalendarioDto calendario);
}
