package br.com.nlw.events.application.exception.custom.users;

public class UserIndicationNotFoundException extends RuntimeException {
    public UserIndicationNotFoundException(String message) {
        super(message);
    }
}
