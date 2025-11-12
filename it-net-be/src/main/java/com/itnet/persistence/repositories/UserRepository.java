package com.itnet.persistence.repositories;

import com.itnet.persistence.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for UserApp entity.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 *
 * @author caito
 *
 */
public interface UserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByEmail(String email);
    boolean existsByEmail(String email);
}
