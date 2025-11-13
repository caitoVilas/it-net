package com.itnet.persistence.repositories;

import com.itnet.persistence.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for UserApp entity.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 *
 * @author caito
 *
 */
public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
    boolean existsByEmail(String email);
    List<UserApp> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    @Query("SELECT u FROM UserApp u WHERE u.email = :email AND u.id <> :id")
    boolean getByEmailAndIdNoEqual(@Param("email") String email, @Param("id") Long id);
}
