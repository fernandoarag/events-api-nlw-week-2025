package br.com.nlw.events.application.exception;

public interface ApiErrorResponse {
    String getType();

    String getTitle();

    int getStatus();
}