package br.com.nlw.events.application.usecases.user.gateway;

import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.domain.model.User;

public interface SearchForUserByEmailAndRegisterIfNotFound {
    User execute(final User user, final Event event);
}
