package br.com.nlw.events.application.usecases.user.gateway;

import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.User;

public interface SearchForUserByEmailAndRegisterIfNotFound {
    User execute(final User user, final Event event);
}
