package com.runninglive.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

/**
 * User: alarive
 */
@Repository
@RestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}