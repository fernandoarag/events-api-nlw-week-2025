package br.com.nlw.events.interfaces.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    @Email
    @NotBlank(message = "Email cannot is empty")
    private String email;

    @NotBlank(message = "Password cannot is empty")
    private String password;
}