package com.itnet.api.models.requests;

import lombok.*;

import java.io.Serializable;

/**
 * Request model for user-related operations.
 * Includes fields for first name, last name, email, password, and repeat password.
 *
 * @author caito
 *
 */
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter@Builder
public class UserRequest implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
}
