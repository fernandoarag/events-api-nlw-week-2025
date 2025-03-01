package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.models.Role;

import java.util.Set;

public interface RoleGateway {
    Set<Role> findAllById(Set<Long> roleIds);

    Role findRoleByName (String role);
}
