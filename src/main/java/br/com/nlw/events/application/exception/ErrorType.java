package br.com.nlw.events.application.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    EVENT_NOT_FOUND("Event not found!"),
    EVENT_CONFLICT("Event conflict!"),
    SUBSCRIPTION_CONFLICT("Subscription conflict!"),
    USER_INDICATION_NOT_FOUND("User indication not found!"),
    SUCCESS("Success!");

    private final String title;

    ErrorType(String title) {
        this.title = title;
    }
}