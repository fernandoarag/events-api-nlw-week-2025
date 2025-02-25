package br.com.nlw.events.application.exception.custom;

public class EventAlreadyExistsException extends RuntimeException {

    public EventAlreadyExistsException(String message) {
        super(message);
    }
}
