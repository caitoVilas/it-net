package com.itnet.services.impl;

import com.itnet.api.exceptions.customs.BadRequestException;
import com.itnet.api.exceptions.customs.NotFoundException;
import com.itnet.api.models.requests.UpdateUserRequest;
import com.itnet.api.models.requests.UserRequest;
import com.itnet.api.models.responses.UserResponse;
import com.itnet.persistence.entities.UserApp;
import com.itnet.persistence.repositories.UserRepository;
import com.itnet.services.contracts.UserService;
import com.itnet.services.helpers.ValidationHelper;
import com.itnet.utils.logs.WriteLogs;
import com.itnet.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the UserService interface for managing user operations.
 *
 * @author caito
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Creates a new user based on the provided UserRequest.
     *
     * @param request the user request containing user details
     */
    @Override
    @Transactional
    public void createUser(UserRequest request) {
        log.info(WriteLogs.logInfo("--> Create User Service"));
        this.validateUser(request);
        UserApp user = UserMapper.mapToUser(request);
        //encodear password
        //agreagar roles
        userRepository.save(user);
        log.info(WriteLogs.logInfo("--> User created successfully with email: " + user.getEmail()));
        //generar el token de validacion y enviarlo por email
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserResponse containing user details
     * @throws NotFoundException if the user is not found
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        log.info(WriteLogs.logInfo("--> Get User by ID Service"));
        return UserMapper.mapToDto(userRepository.findById(id).orElseThrow(
                () -> {
                    log.warn(WriteLogs.logWarning("--> User not found with ID: " + id));
                    return new NotFoundException("User not found with ID: " + id);
                }));
    }

    /**
     * Retrieves all users.
     *
     * @return a list of UserResponse containing details of all users
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        log.info(WriteLogs.logInfo("--> Get Users Service"));
        return userRepository.findAll().stream().map(UserMapper::mapToDto).collect(Collectors.toList());
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email the email of the user to retrieve
     * @return the UserResponse containing user details
     * @throws NotFoundException if the user is not found
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        log.info(WriteLogs.logInfo("--> Get User by Email Service"));
        return UserMapper.mapToDto(userRepository.findByEmail(email).orElseThrow(
          () -> {
              log.warn(WriteLogs.logWarning("--> User not found with Email: " + email));
              return new NotFoundException("User not found with Email: " + email);
          }));
    }

    /**
     * Retrieves users by their first or last name containing the specified username.
     *
     * @param username the username to search for
     * @return a list of UserResponse containing details of matching users
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUserByName(String username) {
        return userRepository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(username, username)
                .stream()
                .map(UserMapper::mapToDto)
                .toList();
    }

    /**
     * Updates an existing user with the provided UpdateUserRequest.
     *
     * @param id      the ID of the user to update
     * @param request the update user request containing updated details
     * @return the UserResponse containing updated user details
     * @throws NotFoundException   if the user is not found
     * @throws BadRequestException if validation fails
     */
    @Override
    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        log.info(WriteLogs.logInfo("--> Update User Service"));
        var user = userRepository.findById(id).orElseThrow(
                () -> {
                    log.warn(WriteLogs.logWarning("--> User not found with ID: " + id));
                    return new NotFoundException("User not found with ID: " + id);
                });
        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            if (!ValidationHelper.validateEmail(request.getEmail())) {
                log.warn(WriteLogs.logWarning("--> Email is not valid: " + request.getEmail()));
                throw new BadRequestException(List.of("Email is not valid."));
            } else if (userRepository.getByEmailAndIdNoEqual(request.getEmail(), id)) {
                log.warn(WriteLogs.logWarning("--> Email is already in use: " + request.getEmail()));
                throw new BadRequestException(List.of("Email is already in use."));
            }
                user.setEmail(request.getEmail());
        }

        log.info(WriteLogs.logInfo("--> User updated successfully with ID: " + id));
        return UserMapper.mapToDto(userRepository.save(user));
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @throws NotFoundException if the user is not found
     */
    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info(WriteLogs.logInfo("--> Delete User Service"));
        var user = userRepository.findById(id).orElseThrow(
                () -> {
                    log.warn(WriteLogs.logWarning("--> User not found with ID: " + id));
                    return new NotFoundException("User not found with ID: " + id);
                });
        userRepository.delete(user);
        log.info(WriteLogs.logInfo("--> User deleted successfully with ID: " + id));

    }


    /**
     * Validates the user request data.
     *
     * @param userRequest the user request to validate
     * @throws BadRequestException if validation fails
     */
    private void validateUser(UserRequest userRequest) {
        log.info(WriteLogs.logInfo("--> Validating User..."));
        List<String> errors = new ArrayList<>();
        if (userRequest.getFirstName() == null || userRequest.getFirstName().isEmpty()) {
            errors.add("First name is required.");
        }
        if (userRequest.getLastName() == null || userRequest.getLastName().isEmpty()) {
            errors.add("Last name is required.");
        }
        if (userRequest.getEmail() == null || userRequest.getEmail().isEmpty()) {
            errors.add("Email is required.");
        } else if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            errors.add("Email is already in use.");
        } else if (!ValidationHelper.validateEmail(userRequest.getEmail())) {
            errors.add("Email is not valid.");
        }
        //validar password
        if (!errors.isEmpty()){
            log.error(WriteLogs.logError("--> User validation failed: " + String.join(", ", errors)));
            throw new BadRequestException(errors);
        }
    }
}
