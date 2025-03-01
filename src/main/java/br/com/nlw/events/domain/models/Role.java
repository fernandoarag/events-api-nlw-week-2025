package br.com.nlw.events.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;
    private String name;
    
    @JsonIgnore
    private Set<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
