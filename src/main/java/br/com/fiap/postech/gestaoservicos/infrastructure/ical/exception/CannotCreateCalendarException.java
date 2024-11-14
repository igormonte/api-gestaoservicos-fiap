package br.com.fiap.postech.gestaoservicos.infrastructure.ical.exception;

public class CannotCreateCalendarException extends RuntimeException {

    public CannotCreateCalendarException() {
        super("Unnable to create calendar!");
    }

    public CannotCreateCalendarException(String message) {
        super(message);
    }

}
