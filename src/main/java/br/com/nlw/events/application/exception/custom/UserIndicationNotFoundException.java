package br.com.nlw.events.application.exception.custom;

public class UserIndicationNotFoundException extends RuntimeException {
    public UserIndicationNotFoundException(String message) {
        super(message);
    }
}
