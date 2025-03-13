package br.com.nlw.events.application.exception.custom.events;

public class EventAlreadyExistsException extends RuntimeException {

    public EventAlreadyExistsException(String message) {
        super(message);
    }
}
