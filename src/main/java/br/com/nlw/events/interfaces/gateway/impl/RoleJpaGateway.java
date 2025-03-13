package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.domain.models.Role;
import br.com.nlw.events.infrastructure.entity.RoleEntity;
import br.com.nlw.events.infrastructure.mapper.RoleMapper;
import br.com.nlw.events.infrastructure.repositories.RoleRepository;
import br.com.nlw.events.interfaces.gateway.database.RoleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleJpaGateway implements RoleGateway {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Set<Role> findAllById(Set<Long> roleIds) {
        final Set<RoleEntity> roles = new HashSet<>();
        if (roleIds != null && !roleIds.isEmpty()) {
            roles.addAll(roleRepository.findAllById(roleIds));
        } else {
            roles.add(roleRepository.findRoleEntityByName("USER"));
        }
        return roleMapper.toDomain(roles);
    }

}
