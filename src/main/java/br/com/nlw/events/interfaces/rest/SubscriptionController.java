package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.subscription.gateway.CreateSubscriptionUseCase;
import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.adapter.CreateSubscriptionRestAdapter;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionResponseDTO;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final CreateSubscriptionUseCase createSubscriptionUseCase;
    private final CreateSubscriptionRestAdapter createSubscriptionRestAdapter;

    public SubscriptionController(
            CreateSubscriptionRestAdapter createSubscriptionRestAdapter,
            CreateSubscriptionUseCase createSubscriptionUseCase
    ) {
        this.createSubscriptionUseCase = createSubscriptionUseCase;
        this.createSubscriptionRestAdapter = createSubscriptionRestAdapter;
    }

    @PostMapping({"/{eventPrettyName}", "/{eventPrettyName}/{userIndicatorId}"})
    public ResponseEntity<SubscriptionResponseDTO> createNewSubscription(
            @PathVariable final String eventPrettyName,
            @PathVariable(required = false) final Long userIndicatorId,
            @RequestBody final UserRequestDTO userRequest
    ) {
        final User userReq = createSubscriptionRestAdapter.toDomain(userRequest);
        final Subscription subscription = createSubscriptionUseCase.execute(eventPrettyName, userIndicatorId, userReq);
        final SubscriptionResponseDTO responseDTO = createSubscriptionRestAdapter.toResponse(subscription);
        return ResponseEntity.ok(responseDTO);
    }

}
