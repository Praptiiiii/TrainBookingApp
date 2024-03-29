package com.example.TrainBookingSystem.service;

import com.example.TrainBookingSystem.dto.AuthResponseDTO;
import com.example.TrainBookingSystem.dto.LoginRequestDTO;
import com.example.TrainBookingSystem.dto.SignUpRequestDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {
    /**
     * Verifies a user based on the provided verification request data.
     *
     * @return AuthResponseDTO containing user details and an access token upon successful verification.
     */
    AuthResponseDTO verify(LoginRequestDTO loginRequestDTO);
    /**
     * Creates a new user account based on the provided sign-up request data.
     *
     * @return AuthResponseDTO containing the newly created user's details.
     */
    AuthResponseDTO createUser(SignUpRequestDTO signUpRequestDTO);

    /**
     * Return user id of the authenticated user.
     *
     * @return String containing the unique user id.
     */
    String getUserId(Authentication authentication);
}

