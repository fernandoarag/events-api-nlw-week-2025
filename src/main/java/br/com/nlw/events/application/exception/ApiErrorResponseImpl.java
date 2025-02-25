package br.com.nlw.events.application.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiErrorResponseImpl implements ApiErrorResponse {
    private final String type;
    private final String title;
    private final int status;
    private final String detail;
    private final LocalDateTime timestamp;

    public ApiErrorResponseImpl(String type, String title, int status, String detail) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timestamp = LocalDateTime.now();
    }
}