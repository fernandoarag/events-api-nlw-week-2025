package br.com.nlw.events.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String role; // ROLE_USER, ROLE_ADMIN, etc.

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
