package com.itnet.api.controllers.impl;

import com.itnet.api.controllers.contracts.UserController;
import com.itnet.api.models.requests.UpdateUserRequest;
import com.itnet.api.models.requests.UserRequest;
import com.itnet.api.models.responses.UserResponse;
import com.itnet.services.contracts.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation of the UserController interface for handling user-related endpoints.
 *
 * @author caito
 *
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Endpoints for managing users")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<?> createUser(UserRequest request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<UserResponse> getUsersById(Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        var users = userService.getUsers();
        if (users.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getUsersByName(String name) {
        var users = userService.getUserByName(name);
        if (users.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserResponse> getUsersByEmail(String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long id, UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
