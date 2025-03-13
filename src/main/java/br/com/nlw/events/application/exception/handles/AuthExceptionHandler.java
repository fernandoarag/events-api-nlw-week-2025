package br.com.nlw.events.application.exception.handles;

import br.com.nlw.events.application.exception.ApiErrorResponseImpl;
import br.com.nlw.events.application.exception.ErrorType;
import br.com.nlw.events.application.exception.custom.auth.InvalidTokenException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthExceptionHandler {

    private static final HttpStatus FORBIDDEN = HttpStatus.FORBIDDEN;
    private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    private static final ErrorType TOKEN_INVALID = ErrorType.TOKEN_INVALID;

    public ApiErrorResponseImpl handleInvalidTokenException(InvalidTokenException e) {
        return new ApiErrorResponseImpl(
                TOKEN_INVALID.name(),
                TOKEN_INVALID.getTitle(),
                FORBIDDEN.value(),
                e.getMessage()
        );
    }

    public ApiErrorResponseImpl handleSignatureException(SignatureException e) {
        return new ApiErrorResponseImpl(
                TOKEN_INVALID.name(),
                TOKEN_INVALID.getTitle(),
                INTERNAL_SERVER_ERROR.value(),
                e.getMessage()
        );
    }

}