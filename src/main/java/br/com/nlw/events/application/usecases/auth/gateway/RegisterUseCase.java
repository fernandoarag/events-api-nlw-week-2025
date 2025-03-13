package br.com.nlw.events.application.usecases.auth.gateway;

import br.com.nlw.events.interfaces.dtos.auth.*;

public interface RegisterUseCase {
    AuthResponseDTO execute(RegisterRequestDTO request);
}