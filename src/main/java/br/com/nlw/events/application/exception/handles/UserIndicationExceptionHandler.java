package br.com.nlw.events.application.exception.handles;

import br.com.nlw.events.application.exception.ApiErrorResponseImpl;
import br.com.nlw.events.application.exception.ErrorType;
import br.com.nlw.events.application.exception.custom.UserIndicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class UserIndicationExceptionHandler {

    private static final ErrorType USER_INDICATION_NOT_FOUND = ErrorType.USER_INDICATION_NOT_FOUND;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    public ApiErrorResponseImpl handleUserIndicationNotFoundException(UserIndicationNotFoundException e) {
        return new ApiErrorResponseImpl(
                USER_INDICATION_NOT_FOUND.name(),
                USER_INDICATION_NOT_FOUND.getTitle(),
                NOT_FOUND.value(),
                e.getMessage()
        );
    }
}