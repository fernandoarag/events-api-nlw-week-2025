package br.com.nlw.events.application.exception.handles;

import br.com.nlw.events.application.exception.ApiErrorResponse;
import br.com.nlw.events.application.exception.custom.SubscriptionConflictException;
import br.com.nlw.events.application.exception.custom.auth.InvalidTokenException;
import br.com.nlw.events.application.exception.custom.events.EventAlreadyExistsException;
import br.com.nlw.events.application.exception.custom.events.EventNotFoundException;
import br.com.nlw.events.application.exception.custom.users.UserAlreadyExistsException;
import br.com.nlw.events.application.exception.custom.users.UserIndicationNotFoundException;
import br.com.nlw.events.application.exception.custom.users.UserNotFoundException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final UserExceptionHandler userExceptionHandler;
    private final AuthExceptionHandler authExceptionHandler;
    private final EventsExceptionHandler eventsExceptionHandler;
    private final SubscriptionsExceptionHandler subscriptionsExceptionHandler;

    public GlobalExceptionHandler(
            UserExceptionHandler userExceptionHandler,
            AuthExceptionHandler authExceptionHandler,
            EventsExceptionHandler eventsExceptionHandler,
            SubscriptionsExceptionHandler subscriptionsExceptionHandler
    ) {
        this.userExceptionHandler = userExceptionHandler;
        this.authExceptionHandler = authExceptionHandler;
        this.eventsExceptionHandler = eventsExceptionHandler;
        this.subscriptionsExceptionHandler = subscriptionsExceptionHandler;
    }

    // Events Exceptions
    @ExceptionHandler(EventNotFoundException.class)
    public ApiErrorResponse handleEventNotFoundException(EventNotFoundException e) {
        return eventsExceptionHandler.handleEventNotFoundException(e);
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public ApiErrorResponse handleEventAlreadyExistsException(EventAlreadyExistsException e) {
        return eventsExceptionHandler.handleEventAlreadyExistsException(e);
    }

    // Users Exceptions
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ApiErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return userExceptionHandler.handleUserAlreadyExistsException(e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ApiErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        return userExceptionHandler.handleUserNotFoundException(e);
    }

    @ExceptionHandler(UserIndicationNotFoundException.class)
    public ApiErrorResponse handleUserIndicationNotFoundException(UserIndicationNotFoundException e) {
        return userExceptionHandler.handleUserIndicationNotFoundException(e);
    }

    // Subscriptions Exceptions
    @ExceptionHandler(SubscriptionConflictException.class)
    public ApiErrorResponse handleSubscriptionConflictException(SubscriptionConflictException e) {
        return subscriptionsExceptionHandler.handleSubscriptionConflictException(e);
    }

    // Auth Exceptions
    @ExceptionHandler(InvalidTokenException.class)
    public ApiErrorResponse handleInvalidTokenException(InvalidTokenException e) {
        return authExceptionHandler.handleInvalidTokenException(e);
    }

    @ExceptionHandler(SignatureException.class)
    public ApiErrorResponse handleSignatureException(SignatureException e) {
        return authExceptionHandler.handleSignatureException(e);
    }
}