package br.com.nlw.events.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@Table(name = "tbl_roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
