package br.com.nlw.events.application.usecases.invite.gateway;

public interface GetInviteHintsByPrettyNameAndUserIdUseCase {
    Long execute(final String eventPrettyName, final Long userId);
}
