package com.mb.auth.security.services;

import com.mb.auth.dto.request.LoginRequest;
import com.mb.auth.dto.request.SignupRequest;

public interface AuthorizationService {
    void signup(SignupRequest signUpRequest);

    String authenticate(LoginRequest loginRequest);

    boolean validate(String token);
}
