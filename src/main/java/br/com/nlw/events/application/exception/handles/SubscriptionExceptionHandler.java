package br.com.nlw.events.application.exception.handles;

import br.com.nlw.events.application.exception.ApiErrorResponseImpl;
import br.com.nlw.events.application.exception.ErrorType;
import br.com.nlw.events.application.exception.custom.SubscriptionConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionExceptionHandler {

    private static final ErrorType SUBSCRIPTION_CONFLICT = ErrorType.SUBSCRIPTION_CONFLICT;
    private static final HttpStatus CONFLICT = HttpStatus.CONFLICT;

    public ApiErrorResponseImpl handleSubscriptionConflictException(SubscriptionConflictException e) {
        return new ApiErrorResponseImpl(
                SUBSCRIPTION_CONFLICT.name(),
                SUBSCRIPTION_CONFLICT.getTitle(),
                CONFLICT.value(),
                e.getMessage()
        );
    }
}