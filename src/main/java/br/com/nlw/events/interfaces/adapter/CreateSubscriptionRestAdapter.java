package br.com.nlw.events.interfaces.adapter;

import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionResponseDTO;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateSubscriptionRestAdapter {

    public User toDomain(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return new User(dto.username(), dto.password(), dto.email());
    }

    public SubscriptionResponseDTO toResponse(Subscription saved) {
        if (saved == null) {
            return null;
        }
        final String designation = saved.getEvent().getPrettyName() + "/" + saved.getSubscriber().getId();
        return new SubscriptionResponseDTO(saved.getSubscriptionNumber(), designation);
    }
}
