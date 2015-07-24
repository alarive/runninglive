package com.runninglive.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User: alarive
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

}