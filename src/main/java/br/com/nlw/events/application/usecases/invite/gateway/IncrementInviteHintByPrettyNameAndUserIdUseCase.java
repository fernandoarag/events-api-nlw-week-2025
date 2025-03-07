package br.com.nlw.events.application.usecases.invite.gateway;

public interface IncrementInviteHintByPrettyNameAndUserIdUseCase {
    void execute(final Long subscriptionNumber);
}
