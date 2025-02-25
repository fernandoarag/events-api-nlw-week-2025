package br.com.nlw.events.application.exception.handles;

import br.com.nlw.events.application.exception.ApiErrorResponseImpl;
import br.com.nlw.events.application.exception.ErrorType;
import br.com.nlw.events.application.exception.custom.EventAlreadyExistsException;
import br.com.nlw.events.application.exception.custom.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class EventExceptionHandler {

    private static final ErrorType EVENT_NOT_FOUND = ErrorType.EVENT_NOT_FOUND;
    private static final ErrorType EVENT_CONFLICT = ErrorType.EVENT_CONFLICT;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    public ApiErrorResponseImpl handleEventNotFoundException(EventNotFoundException e) {
        return new ApiErrorResponseImpl(
                EVENT_NOT_FOUND.name(),
                EVENT_NOT_FOUND.getTitle(),
                NOT_FOUND.value(),
                e.getMessage()
        );
    }

    public ApiErrorResponseImpl handleEventAlreadyExistsException(EventAlreadyExistsException e) {
        return new ApiErrorResponseImpl(
                EVENT_CONFLICT.name(),
                EVENT_CONFLICT.getTitle(),
                HttpStatus.CONFLICT.value(),
                e.getMessage()
        );
    }
}