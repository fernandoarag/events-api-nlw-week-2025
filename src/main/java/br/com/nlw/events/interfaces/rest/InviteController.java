package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.invite.gateway.GetInviteHintsByPrettyNameAndUserIdUseCase;
import br.com.nlw.events.application.usecases.invite.gateway.IncrementInviteHintByPrettyNameAndUserIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invite")
public class InviteController {

    private final GetInviteHintsByPrettyNameAndUserIdUseCase getInviteHintsByPrettyNameAndUserIdUseCase;
    private final IncrementInviteHintByPrettyNameAndUserIdUseCase incrementInviteHintByPrettyNameAndUserIdUseCase;

    public InviteController(GetInviteHintsByPrettyNameAndUserIdUseCase getInviteHintsByPrettyNameAndUserIdUseCase, IncrementInviteHintByPrettyNameAndUserIdUseCase incrementInviteHintByPrettyNameAndUserIdUseCase) {
        this.getInviteHintsByPrettyNameAndUserIdUseCase = getInviteHintsByPrettyNameAndUserIdUseCase;
        this.incrementInviteHintByPrettyNameAndUserIdUseCase = incrementInviteHintByPrettyNameAndUserIdUseCase;
    }

    @GetMapping("/hints/{eventPrettyName}/{userId}")
    public ResponseEntity<Long> getHints(
            @PathVariable String eventPrettyName,
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(getInviteHintsByPrettyNameAndUserIdUseCase.execute(eventPrettyName, userId));
    }

    @PatchMapping("/hints/{subscriptionNumber}")
    public ResponseEntity<?> updateHints(
            @PathVariable Long subscriptionNumber
    ) {
        incrementInviteHintByPrettyNameAndUserIdUseCase.execute(subscriptionNumber);
        return ResponseEntity.noContent().build();
    }
}
