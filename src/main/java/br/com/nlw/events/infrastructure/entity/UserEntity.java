package br.com.nlw.events.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roles")
@Table(name = "tbl_users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    private String password;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Verifica se a lista de roles não está vazia
        if (roles != null && !roles.isEmpty()) {
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))  // Associe as roles com o prefixo 'ROLE_'
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();  // Retorna uma lista vazia se não houver roles
    }


}