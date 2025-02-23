package br.com.nlw.events.service;

import br.com.nlw.events.dto.SubscriptionResponseDTO;
import br.com.nlw.events.exception.EventNotFoundException;
import br.com.nlw.events.exception.SubscriptionConflictException;
import br.com.nlw.events.exception.UserIndicationNotFoundException;
import br.com.nlw.events.model.Event;
import br.com.nlw.events.model.Subscription;
import br.com.nlw.events.model.User;
import br.com.nlw.events.repo.EventRepository;
import br.com.nlw.events.repo.SubscriptionRepository;
import br.com.nlw.events.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(EventRepository eventRepository, UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public SubscriptionResponseDTO createSubscription(String eventName, User user, Integer userId) {
        // Recuperar o evento pelo nome
        final Event event = eventRepository.findByPrettyName(eventName);
        if(event == null) {
            throw new EventNotFoundException("Event: " + eventName + ", does not exist!");
        }

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            existingUser = userRepository.save(user);
        }

        final User indicationUser = userRepository.findById(userId).orElse(null);
        if(indicationUser == null) {
            throw new UserIndicationNotFoundException("Referral user: " + userId + ", not found!");
        }

        final Subscription subscription = new Subscription();
        subscription.setEvent(event);
        subscription.setSubscriber(existingUser);
        subscription.setIndication(indicationUser);

        Subscription tmpSub = subscriptionRepository.findByEventAndSubscriber(event, existingUser);
        if (tmpSub != null) {
            throw new SubscriptionConflictException("There is already a registration for the user: '" + existingUser.getEmail() + "', in the event '" + event.getTitle() + "'");
        }

        Subscription res = subscriptionRepository.save(subscription);
        return new SubscriptionResponseDTO(res.getSubscriptionNumber(), "http://codecraft.com/subscription/" + res.getEvent().getPrettyName() + "/" + res.getSubscriber().getId());
    }
}
