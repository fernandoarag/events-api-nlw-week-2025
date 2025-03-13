package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.models.Role;
import br.com.nlw.events.infrastructure.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RoleMapper {

    public Set<Role> toDomain(Set<RoleEntity> roleEntities) {
        final Set<Role> roles = new HashSet<>();
        roleEntities.forEach(roleEntity ->
                roles.add(new Role(roleEntity.getId(), roleEntity.getName())));
        return roles;
    }

    public List<String> toStringDomain(Set<RoleEntity> roleEntities) {
        final List<String> roles = new ArrayList<>();
        roleEntities.forEach(roleEntity -> roles.add(roleEntity.getName()));
        return roles;
    }

    public Role toDomain(RoleEntity roleEntity) {
        return new Role(roleEntity.getId(), roleEntity.getName());
    }

    public Set<RoleEntity> toEntity(Set<Role> roles) {
        final Set<RoleEntity> roleEntities = new HashSet<>();
        if (roles != null) {
            roles.forEach(role ->
                    roleEntities.add(new RoleEntity(role.getId(), role.getName())));
            return roleEntities;
        }
        return roleEntities;
    }
}