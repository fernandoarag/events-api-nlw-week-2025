package br.com.nlw.events.application.usecases.user.impl;

import br.com.nlw.events.application.usecases.user.gateway.FindUserByUsernameUseCase;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserByUsernameUseCaseImpl implements FindUserByUsernameUseCase {

    private final UserGateway userGateway;

    public FindUserByUsernameUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(final String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return userGateway.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name: " + username + " not found!"));
    }
}
