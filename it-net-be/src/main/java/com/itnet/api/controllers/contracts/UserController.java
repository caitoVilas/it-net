package com.itnet.api.controllers.contracts;

import com.itnet.api.models.requests.UpdateUserRequest;
import com.itnet.api.models.requests.UserRequest;
import com.itnet.api.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController interface defining endpoints for user operations.
 *
 * @author caito
 *
 */
public interface UserController {

    @PostMapping()
    @Operation(description = "Add a new user")
    @Parameter(name = "request", description = "user request object containing details of the user to be added")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "user added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> createUser(@RequestBody UserRequest request);

    @GetMapping("/{id}")
    @SecurityRequirement(name = "security token")
    @Operation(description = "Retrieve users by ID")
    @Parameter(name = "id", description = "ID of user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "users retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserResponse> getUsersById(@PathVariable Long id);

    @GetMapping
    @SecurityRequirement(name = "security token")
    @Operation(description = "Retrieve all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "users retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<UserResponse>> getAllUsers();

    @GetMapping("/by-name/{name}")
    @SecurityRequirement(name = "security token")
    @Operation(description = "Retrieve users by name")
    @Parameter(name = "name", description = "name of user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "users retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<UserResponse>> getUsersByName(@PathVariable String name);

    @GetMapping("/by-email/{email}")
    @SecurityRequirement(name = "security token")
    @Operation(description = "Retrieve users by email")
    @Parameter(name = "email", description = "email of user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "users retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserResponse> getUsersByEmail(@PathVariable String email);

    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "security token")
    @Operation(description = "Update an existing user")
    @Parameter(name = "id", description = "ID of the user to update")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "users updated successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request);

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "security token")
    @Operation(description = "Delete an existing user")
    @Parameter(name = "id", description = "ID of the user to delete")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "user deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> deleteUser(@PathVariable Long id);
}
