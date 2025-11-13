package com.itnet.api.models.requests;

import lombok.*;

import java.io.Serializable;

/**
 * Request model for updating user information.
 *
 * @author caito
 *
 */
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter@Builder
public class UpdateUserRequest implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
}
