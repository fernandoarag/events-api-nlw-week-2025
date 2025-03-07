package br.com.nlw.events.application.usecases.invite.gateway;

import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.Invite;
import br.com.nlw.events.domain.models.User;
import org.springframework.stereotype.Service;

@Service
public interface FindInviteBySubscriberIdAndEventIdUseCase {
    Invite execute(final Event event, final User existingUser);
}
