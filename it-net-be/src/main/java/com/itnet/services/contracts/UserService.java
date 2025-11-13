package com.itnet.services.contracts;

import com.itnet.api.models.requests.UpdateUserRequest;
import com.itnet.api.models.requests.UserRequest;
import com.itnet.api.models.responses.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * User service interface for managing user operations.
 *
 * @author caito
 *
 */
public interface UserService {

    void createUser(UserRequest request);
    UserResponse getUser(Long id);
    List<UserResponse> getUsers();
    UserResponse getUserByEmail(String email);
    List<UserResponse> getUserByName(String username);
    UserResponse updateUser(Long id,UpdateUserRequest request);
    void deleteUser(Long id);
}
