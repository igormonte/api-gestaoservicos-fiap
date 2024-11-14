package br.com.fiap.postech.gestaoservicos.infrastructure.ical.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto.IcalCalendarioDto;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.dto.IcalEventoDto;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.exception.CannotCreateCalendarException;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale;
import net.fortuna.ical4j.model.property.immutable.ImmutableVersion;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class IcalService implements IcalRepository {

    public VEvent gerarEvento(IcalEventoDto evento) {
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone("America/Sao_Paulo");
        VTimeZone tz = timezone.getVTimeZone();
        VEvent meeting = new VEvent(evento.dataInicial(), evento.dataFinal(), evento.titulo());
        if(evento.descricao() != null && !evento.descricao().isEmpty()) {
            meeting.getProperties().add(new Description(evento.descricao()));

        }

        // add timezone info..
        meeting.add(tz);

        // generate unique identifier..
        UidGenerator ug = new RandomUidGenerator();
        Uid uid = ug.generateUid();
        meeting.add(uid);

        return meeting;
    }

    @Override
    public byte[] gerarCalendario(IcalCalendarioDto calendario) {
        try {

            File file = new File("mycalendar.ics");
            FileOutputStream fout = new FileOutputStream(file);

            CalendarOutputter outputter = new CalendarOutputter();
            Calendar calendar = new Calendar();
            calendar.add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
            calendar.add(ImmutableVersion.VERSION_2_0);
            calendar.add(ImmutableCalScale.GREGORIAN);

            for(IcalEventoDto evento:calendario.evento()) {
                calendar.add(this.gerarEvento(evento));
            }

            outputter.output(calendar, fout);

            return Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            throw new CannotCreateCalendarException(e.getMessage());
        }


    }
}
