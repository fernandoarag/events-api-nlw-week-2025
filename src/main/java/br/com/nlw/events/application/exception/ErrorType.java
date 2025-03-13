package br.com.nlw.events.application.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    USER_NOT_FOUND("User not found!"),
    USER_ALREADY_EXISTS("User already exists!"),
    USER_INDICATION_NOT_FOUND("User indication not found!"),

    EVENT_CONFLICT("Event conflict!"),
    EVENT_NOT_FOUND("Event not found!"),

    SUBSCRIPTION_CONFLICT("Subscription conflict!"),

    TOKEN_INVALID("Invalid Token");

    private final String title;

    ErrorType(String title) {
        this.title = title;
    }
}