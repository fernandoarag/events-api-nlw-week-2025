package br.com.nlw.events.infrastructure.repositories;

import br.com.nlw.events.infrastructure.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByName(String name);
}


