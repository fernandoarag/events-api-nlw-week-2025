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
    public ResponseEntity<Integer> getHints(
            @PathVariable String eventPrettyName,
            @PathVariable Integer userId
    ) {
        return ResponseEntity.ok(getInviteHintsByPrettyNameAndUserIdUseCase.execute(eventPrettyName, userId));
    }

    @PatchMapping("/hints/{eventPrettyName}/{userId}")
    public ResponseEntity<?> updateHints(
            @PathVariable String eventPrettyName,
            @PathVariable Integer userId
    ) {
        incrementInviteHintByPrettyNameAndUserIdUseCase.execute(eventPrettyName, userId);
        return ResponseEntity.noContent().build();
    }
}
