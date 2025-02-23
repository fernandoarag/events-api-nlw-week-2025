package br.com.nlw.events.service;

import br.com.nlw.events.dto.SubscriptionRankingByUser;
import br.com.nlw.events.dto.SubscriptionRankingItem;
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

import java.util.List;
import java.util.stream.IntStream;

@Service
public class SubscriptionService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(
            EventRepository eventRepository,
            UserRepository userRepository,
            SubscriptionRepository subscriptionRepository
    ) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public SubscriptionResponseDTO createSubscription(String eventName, User user, Integer userId) {
        // Recuperar o evento pelo nome
        final Event event = eventRepository.findByPrettyName(eventName);
        if (event == null) {
            throw new EventNotFoundException("Event: " + eventName + ", does not exist!");
        }

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            existingUser = userRepository.save(user);
        }

        User indicatorUser = null;
        if (userId != null) {
            indicatorUser = userRepository.findById(userId).orElse(null);
            if (indicatorUser == null) {
                throw new UserIndicationNotFoundException("Referral user: " + userId + ", not found!");
            }
        }

        final Subscription subscription = new Subscription();
        subscription.setEvent(event);
        subscription.setSubscriber(existingUser);
        subscription.setIndication(indicatorUser);

        Subscription tmpSub = subscriptionRepository.findByEventAndSubscriber(event, existingUser);
        if (tmpSub != null) {
            throw new SubscriptionConflictException("There is already a registration for the user: '" + existingUser.getEmail() + "', in the event '" + event.getTitle() + "'");
        }

        Subscription res = subscriptionRepository.save(subscription);
        return new SubscriptionResponseDTO(res.getSubscriptionNumber(), "http://codecraft.com/subscription/" + res.getEvent().getPrettyName() + "/" + res.getSubscriber().getId());
    }

    public List<SubscriptionRankingItem> getCompleteRanking(String prettyName) {
        Event event = eventRepository.findByPrettyName(prettyName);
        if (event == null) {
            throw new EventNotFoundException("Ranking of Event: " + prettyName + ", does not exist!");
        }
        return subscriptionRepository.generateRanking(event.getEventId());
    }

    public SubscriptionRankingByUser getRankingByUser(String prettyName, Integer userId) {
        List<SubscriptionRankingItem> ranking = this.getCompleteRanking(prettyName);

        SubscriptionRankingItem item = ranking.stream()
                .filter(i -> i.userId().equals(userId)).findFirst()
                .orElse(null);
        if (item == null) {
            throw new UserIndicationNotFoundException("There are no registrations with user indication: " + userId);
        }
        int posicao = IntStream.range(0, ranking.size())
                .filter(pos -> ranking.get(pos).userId().equals(userId))
                .findFirst().getAsInt();
        return new SubscriptionRankingByUser(posicao + 1, item);
    }
}
