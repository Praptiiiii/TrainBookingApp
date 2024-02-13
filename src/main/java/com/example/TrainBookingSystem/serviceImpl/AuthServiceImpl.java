package com.example.TrainBookingSystem.serviceImpl;

import com.example.TrainBookingSystem.dto.AuthResponseDTO;
import com.example.TrainBookingSystem.dto.LoginRequestDTO;
import com.example.TrainBookingSystem.dto.SignUpRequestDTO;
import com.example.TrainBookingSystem.entity.User;
import com.example.TrainBookingSystem.repository.UserRepository;
import com.example.TrainBookingSystem.service.AuthService;
import com.example.TrainBookingSystem.utils.ValidationUtils;
import com.example.TrainBookingSystem.utils.impl.JwtUtilsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtilsImpl jwtService;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a TokenVerificationServiceImpl with the necessary dependencies.
     *
     * @param userRepository        The repository for user data.
     * @param jwtService            The service for JWT token operations.
     * @param authenticationManager The authentication manager for user
     *                              authentication.
     * @param passwordEncoder       The password encoder for encoding user
     *                              passwords.
     */
    public AuthServiceImpl(UserRepository userRepository, JwtUtilsImpl jwtService,
                           AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Verifies a user based on the provided verification request data.
     *
     * @param verifyRequestDTO The data transfer object containing information
     *                         needed for user verification.
     * @return VerifyResponseDTO containing user details and an access token upon
     * successful verification.
     * @throws IllegalArgumentException if the provided email or password is
     *                                  invalid.
     */
    @Override
    public AuthResponseDTO verify(LoginRequestDTO verifyRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(verifyRequestDTO.getEmail(),
                        verifyRequestDTO.getPassword()));
        User user = userRepository.findByEmail(verifyRequestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return AuthResponseDTO.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .accessToken(jwt)
                .role(user.getRole())
                .build();
    }

    /**
     * Creates a new user account based on the provided sign-up request data.
     * <p>
     * TODO: Add validation for sign-up request data.
     *
     * @param signUpRequestDTO The data transfer object containing user registration
     *                         information.
     * @return SignUpResponseDTO containing the newly created user's details.
     */
    @Override
    public AuthResponseDTO createUser(SignUpRequestDTO signUpRequestDTO) {

        ValidationUtils.validateSignUpRequest(userRepository, signUpRequestDTO);
        User user = User.builder()
                .name(signUpRequestDTO.getName())
                .email(signUpRequestDTO.getEmail())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .createdAt(new Date(System.currentTimeMillis()))
                .updatedAt(new Date(System.currentTimeMillis()))
                .id(UUID.randomUUID().toString())
                .role(signUpRequestDTO.getRole()).build();
        User savedUser = userRepository.save(user);
        var jwt = jwtService.generateToken(savedUser);
        return AuthResponseDTO.builder()
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .accessToken(jwt)
                .build();
    }
    /**
     * Retrieves the user ID from the provided Authentication object.
     *
     * @param authentication The Authentication object representing the current user.
     * @return The user ID extracted from the Authentication object.
     */
    @Override
    public String getUserId(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getId();
    }
}

