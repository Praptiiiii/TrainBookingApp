package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.controller.AuthController;
import com.example.TrainBookingSystem.dto.AuthResponseDTO;
import com.example.TrainBookingSystem.dto.LoginRequestDTO;
import com.example.TrainBookingSystem.dto.SignUpRequestDTO;
import com.example.TrainBookingSystem.serviceImpl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthServiceImpl authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void loginUserSuccess() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(); // Assume this is properly set up
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(); // Assume this is the expected result

        when(authService.verify(loginRequestDTO)).thenReturn(authResponseDTO);

        ResponseEntity<AuthResponseDTO> response = authController.loginUser(loginRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authResponseDTO, response.getBody());
        verify(authService, times(1)).verify(loginRequestDTO);
    }

    @Test
    void createUserSuccess() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO(); // Assume this is properly set up
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(); // Assume this is the expected result

        when(authService.createUser(signUpRequestDTO)).thenReturn(authResponseDTO);

        ResponseEntity<AuthResponseDTO> response = authController.createUser(signUpRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(authResponseDTO, response.getBody());
        verify(authService, times(1)).createUser(signUpRequestDTO);
    }


}

