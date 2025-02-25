package br.com.nlw.events.application.exception.handles;

import br.com.nlw.events.application.exception.ApiErrorResponseImpl;
import br.com.nlw.events.application.exception.custom.EventAlreadyExistsException;
import br.com.nlw.events.application.exception.custom.EventNotFoundException;
import br.com.nlw.events.application.exception.custom.SubscriptionConflictException;
import br.com.nlw.events.application.exception.custom.UserIndicationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final EventExceptionHandler eventExceptionHandler;
    private final UserIndicationExceptionHandler userIndicationExceptionHandler;
    private final SubscriptionExceptionHandler subscriptionExceptionHandler;

    public GlobalExceptionHandler(EventExceptionHandler eventExceptionHandler, UserIndicationExceptionHandler userIndicationExceptionHandler, SubscriptionExceptionHandler subscriptionExceptionHandler) {
        this.eventExceptionHandler = eventExceptionHandler;
        this.userIndicationExceptionHandler = userIndicationExceptionHandler;
        this.subscriptionExceptionHandler = subscriptionExceptionHandler;
    }

    // Event Exceptions
    @ExceptionHandler(EventNotFoundException.class)
    public ApiErrorResponseImpl handleEventNotFoundException(EventNotFoundException e) {
        return eventExceptionHandler.handleEventNotFoundException(e);
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public ApiErrorResponseImpl handleEventAlreadyExistsException(EventAlreadyExistsException e) {
        return eventExceptionHandler.handleEventAlreadyExistsException(e);
    }

    // User Indication Exceptions
    @ExceptionHandler(UserIndicationNotFoundException.class)
    public ApiErrorResponseImpl handleEventNotFoundException(UserIndicationNotFoundException e) {
        return userIndicationExceptionHandler.handleUserIndicationNotFoundException(e);
    }

    // Subscription Exceptions
    @ExceptionHandler(SubscriptionConflictException.class)
    public ApiErrorResponseImpl handleSubscriptionConflictException(SubscriptionConflictException e) {
        return subscriptionExceptionHandler.handleSubscriptionConflictException(e);
    }
}