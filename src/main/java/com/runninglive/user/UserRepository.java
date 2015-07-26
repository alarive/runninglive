package com.runninglive.user;

import com.runninglive.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

/**
 * User: alarive
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = ?1")
    public User findByUsername(String username);

    @Override
    @PreAuthorize("#user.id == authentication.principal.id")
    <U extends User> U save(@Param("user") U user);

}