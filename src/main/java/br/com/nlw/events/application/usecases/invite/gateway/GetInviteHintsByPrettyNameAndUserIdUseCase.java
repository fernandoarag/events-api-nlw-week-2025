package br.com.nlw.events.application.usecases.invite.gateway;

public interface GetInviteHintsByPrettyNameAndUserIdUseCase {
    Integer execute(final String eventPrettyName, final Integer userId);
}
