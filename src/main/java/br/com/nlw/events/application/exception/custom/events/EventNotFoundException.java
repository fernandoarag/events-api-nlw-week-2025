package br.com.nlw.events.application.exception.custom.events;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String message) {
        super(message);
    }
}
