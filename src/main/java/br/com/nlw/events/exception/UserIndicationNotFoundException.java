package br.com.nlw.events.exception;

public class UserIndicationNotFoundException extends RuntimeException {
    public UserIndicationNotFoundException(String message) {
        super(message);
    }
}
