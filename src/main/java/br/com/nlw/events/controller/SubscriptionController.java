package br.com.nlw.events.controller;

import br.com.nlw.events.dto.ErrorMessage;
import br.com.nlw.events.dto.SubscriptionResponseDTO;
import br.com.nlw.events.exception.EventNotFoundException;
import br.com.nlw.events.exception.SubscriptionConflictException;
import br.com.nlw.events.exception.UserIndicationNotFoundException;
import br.com.nlw.events.model.User;
import br.com.nlw.events.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping({"/{prettyName}", "/{prettyName}/{userId}"})
    public ResponseEntity<?> createNewSubscription(
            @PathVariable final String prettyName,
            @PathVariable(required = false) final Integer userId,
            @RequestBody final User subscriber
    ) {
        try {
            final SubscriptionResponseDTO response = subscriptionService.createSubscription(prettyName, subscriber, userId);
            if (response != null) {
                return ResponseEntity.ok(response);
            }
        } catch (EventNotFoundException | UserIndicationNotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        } catch (SubscriptionConflictException ex) {
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{prettyName}/ranking")
    public ResponseEntity<?> generateRankingByEvent(@PathVariable final String prettyName) {
        try {
            return ResponseEntity.ok(subscriptionService.getCompleteRanking(prettyName).subList(0, 3));
        } catch (EventNotFoundException | UserIndicationNotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        } catch (SubscriptionConflictException ex) {
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        }
    }

    @GetMapping("/{prettyName}/ranking/{userId}")
    public ResponseEntity<?> generateRankingByEventAndUserId(
            @PathVariable final String prettyName,
            @PathVariable final Integer userId
    ) {
        try {
            return ResponseEntity.ok(subscriptionService.getRankingByUser(prettyName, userId));
        } catch (EventNotFoundException | UserIndicationNotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        } catch (SubscriptionConflictException ex) {
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        }
    }
}
