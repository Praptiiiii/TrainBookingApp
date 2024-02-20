package com.example.TrainBookingSystem.serviceImpl;

import com.example.TrainBookingSystem.dto.AuthResponseDTO;
import com.example.TrainBookingSystem.dto.LoginRequestDTO;
import com.example.TrainBookingSystem.dto.SignUpRequestDTO;
import com.example.TrainBookingSystem.entity.User;
import com.example.TrainBookingSystem.enums.Role;
import com.example.TrainBookingSystem.repository.UserRepository;
import com.example.TrainBookingSystem.utils.impl.JwtUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtilsImpl jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void verifySuccess() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@example.com", "password");
        User user = new User();
        user.setId("123");
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setRole(Role.USER);

        when(authenticationManager.authenticate(any()))
                .thenAnswer(invocation -> invocation.getArgument(0)); // Allow any authentication
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("mocked_token");

        AuthResponseDTO responseDTO = authService.verify(loginRequestDTO);

        assertEquals("123", responseDTO.getUserId());
        assertEquals("Test User", responseDTO.getName());
        assertEquals("test@example.com", responseDTO.getEmail());
        assertEquals("USER", responseDTO.getRole().toString());
        assertEquals("mocked_token", responseDTO.getAccessToken());
    }

    @Test
    void verifyInvalidCredentials() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@example.com", "password");

        when(authenticationManager.authenticate(any()))
                .thenThrow(new IllegalArgumentException("Invalid credentials"));
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> authService.verify(loginRequestDTO));
    }

    @Test
    void createUserSuccess() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setName("Test User");
        signUpRequestDTO.setEmail("test@example.com");
        signUpRequestDTO.setPassword("Password123!"); // Updated password to meet validation criteria
        signUpRequestDTO.setRole(Role.USER);

        when(userRepository.save(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId("123");
            return user;
        });
        when(jwtService.generateToken(any())).thenReturn("mocked_token");

        AuthResponseDTO responseDTO = authService.createUser(signUpRequestDTO);

        assertEquals("123", responseDTO.getUserId());
        assertEquals("Test User", responseDTO.getName());
        assertEquals("test@example.com", responseDTO.getEmail());
        assertEquals("USER", responseDTO.getRole().toString());
        assertEquals("mocked_token", responseDTO.getAccessToken());
    }




    // Write your test cases here
}

