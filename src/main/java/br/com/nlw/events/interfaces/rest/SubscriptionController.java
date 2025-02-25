package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.subscription.gateway.CreateSubscriptionUseCase;
import br.com.nlw.events.application.usecases.subscription.gateway.GetCompleteSubscriptionRankingByPrettyNameUseCase;
import br.com.nlw.events.application.usecases.subscription.gateway.GetRankingByUserUseCase;
import br.com.nlw.events.domain.model.Subscription;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.adapter.CreateSubscriptionRestAdapter;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingByUser;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionResponseDTO;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final CreateSubscriptionRestAdapter createSubscriptionRestAdapter;

    private final GetRankingByUserUseCase getRankingByUserUseCase;
    private final CreateSubscriptionUseCase createSubscriptionUseCase;
    private final GetCompleteSubscriptionRankingByPrettyNameUseCase getCompleteSubscriptionRankingByPrettyNameUseCase;

    public SubscriptionController(
            CreateSubscriptionRestAdapter createSubscriptionRestAdapter,
            CreateSubscriptionUseCase createSubscriptionUseCase,
            GetCompleteSubscriptionRankingByPrettyNameUseCase getCompleteSubscriptionRankingByPrettyNameUseCase,
            GetRankingByUserUseCase getRankingByUserUseCase
    ) {
        this.getRankingByUserUseCase = getRankingByUserUseCase;
        this.createSubscriptionUseCase = createSubscriptionUseCase;
        this.createSubscriptionRestAdapter = createSubscriptionRestAdapter;
        this.getCompleteSubscriptionRankingByPrettyNameUseCase = getCompleteSubscriptionRankingByPrettyNameUseCase;
    }

    @PostMapping({"/{eventPrettyName}", "/{eventPrettyName}/{userIndicatorId}"})
    public ResponseEntity<SubscriptionResponseDTO> createNewSubscription(
            @PathVariable final String eventPrettyName,
            @PathVariable(required = false) final Integer userIndicatorId,
            @RequestBody final UserRequestDTO userRequest
    ) {
        final User userReq = createSubscriptionRestAdapter.toDomain(userRequest);
        final Subscription subscription = createSubscriptionUseCase.execute(eventPrettyName, userIndicatorId, userReq);
        final SubscriptionResponseDTO responseDTO = createSubscriptionRestAdapter.toResponse(subscription);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{prettyName}/ranking")
    public ResponseEntity<List<SubscriptionRankingItem>> generateRankingByEvent(@PathVariable final String prettyName) {
        return ResponseEntity.ok(getCompleteSubscriptionRankingByPrettyNameUseCase.execute(prettyName));
    }

    @GetMapping("/{prettyName}/ranking/{userId}")
    public ResponseEntity<SubscriptionRankingByUser> generateRankingByEventAndUserId(
            @PathVariable final String prettyName,
            @PathVariable final Integer userId
    ) {
        final List<SubscriptionRankingItem> ranking = getCompleteSubscriptionRankingByPrettyNameUseCase.execute(prettyName);
        return ResponseEntity.ok(getRankingByUserUseCase.execute(ranking, userId));
    }
}
