package br.com.nlw.events.application.exception.custom;

public class SubscriptionConflictException extends RuntimeException {

    public SubscriptionConflictException(String message) {
        super(message);
    }
}
