package com.itnet.api.models.responses;

import lombok.*;

import java.io.Serializable;

/**
 * Response model for user-related operations.
 * Includes fields for id, first name, last name, and email.
 *
 * @author caito
 *
 */
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter@Builder
public class UserResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
