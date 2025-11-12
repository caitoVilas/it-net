package com.itnet.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a user in the application.
 * Includes fields for id, first name, last name, email, and password.
 *
 * @author caito
 *
 */
@Entity
@Table(name = "users")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter@Builder
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
