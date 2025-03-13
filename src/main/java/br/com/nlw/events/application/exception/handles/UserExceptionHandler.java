package br.com.nlw.events.application.exception.handles;

import br.com.nlw.events.application.exception.ApiErrorResponseImpl;
import br.com.nlw.events.application.exception.ErrorType;
import br.com.nlw.events.application.exception.custom.users.UserAlreadyExistsException;
import br.com.nlw.events.application.exception.custom.users.UserIndicationNotFoundException;
import br.com.nlw.events.application.exception.custom.users.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserExceptionHandler {

    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    private static final HttpStatus CONFLICT = HttpStatus.CONFLICT;

    private static final ErrorType USER_NOT_FOUND = ErrorType.USER_NOT_FOUND;
    private static final ErrorType USER_ALREADY_EXISTS = ErrorType.USER_ALREADY_EXISTS;
    private static final ErrorType USER_INDICATION_NOT_FOUND = ErrorType.USER_INDICATION_NOT_FOUND;

    public ApiErrorResponseImpl handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ApiErrorResponseImpl(
                USER_ALREADY_EXISTS.name(),
                USER_ALREADY_EXISTS.getTitle(),
                CONFLICT.value(),
                e.getMessage()
        );
    }

    public ApiErrorResponseImpl handleUserNotFoundException(UserNotFoundException e) {
        return new ApiErrorResponseImpl(
                USER_NOT_FOUND.name(),
                USER_NOT_FOUND.getTitle(),
                NOT_FOUND.value(),
                e.getMessage()
        );
    }

    public ApiErrorResponseImpl handleUserIndicationNotFoundException(UserIndicationNotFoundException e) {
        return new ApiErrorResponseImpl(
                USER_INDICATION_NOT_FOUND.name(),
                USER_INDICATION_NOT_FOUND.getTitle(),
                NOT_FOUND.value(),
                e.getMessage()
        );
    }
}