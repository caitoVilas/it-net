package com.itnet.utils.mappers;

import com.itnet.api.models.requests.UserRequest;
import com.itnet.api.models.responses.UserResponse;
import com.itnet.persistence.entities.UserApp;

/**
 * Mapper class for converting between UserApp entity and UserRequest/UserResponse DTOs.
 *
 * @author caito
 *
 */
public class UserMapper {

    /**
     * Maps a UserApp entity to a UserResponse DTO.
     *
     * @param user the UserApp entity to map
     * @return the mapped UserResponse DTO
     */
    public static UserResponse mapToDto(UserApp user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    /**
     * Maps a UserRequest DTO to a UserApp entity.
     *
     * @param request the UserRequest DTO to map
     * @return the mapped UserApp entity
     */
    public static UserApp mapToUser(UserRequest request){
       return UserApp.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .accountNonExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(false)
                .build();
    }
}
