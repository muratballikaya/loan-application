package com.mb.auth.controllers;

import com.mb.auth.dto.request.LoginRequest;
import com.mb.auth.dto.request.SignupRequest;
import com.mb.auth.dto.request.ValidateionRequest;
import com.mb.auth.dto.response.MessageResponse;
import com.mb.auth.dto.response.Token;
import com.mb.auth.security.services.AuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        when(authorizationService.authenticate(any(LoginRequest.class))).thenReturn("test-jwt-token");

        // Act
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals("test-jwt-token", ((Token)response.getBody()).getJwt());
    }

    @Test
    void testRegisterUser() {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testuser");
        signupRequest.setPassword("testpassword");

        // Act
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals("User registered successfully!", ((MessageResponse) response.getBody()).getMessage());
    }

    @Test
    void testValidateUser() {
        // Arrange
        ValidateionRequest validateionRequest = new ValidateionRequest();
        validateionRequest.setToken("test-jwt-token");

        when(authorizationService.validate(any(String.class))).thenReturn(true);

        // Act
        ResponseEntity<?> response = authController.validateUser(validateionRequest);

        // Assert
        assertEquals(true, response.getBody());
    }
}