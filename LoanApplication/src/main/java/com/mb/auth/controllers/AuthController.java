package com.mb.auth.controllers;

import com.mb.auth.dto.request.LoginRequest;
import com.mb.auth.dto.request.SignupRequest;
import com.mb.auth.dto.request.ValidateionRequest;
import com.mb.auth.dto.response.MessageResponse;
import com.mb.auth.dto.response.Token;
import com.mb.auth.security.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


  @Autowired
  AuthorizationService authorizationService;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      String jwt = authorizationService.authenticate(loginRequest);
      return ResponseEntity.ok().body(new Token(jwt));
    }catch(Exception e){
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
     try {
       authorizationService.signup(signUpRequest);
       return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
     }catch(Exception e){
       return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
     }
  }


  @PostMapping("/validate")
  public ResponseEntity<?> validateUser(@Valid @RequestBody ValidateionRequest validateionRequest) {
    try {
      return ResponseEntity.ok()
              .body(authorizationService.validate(validateionRequest.getToken()));
    }catch(Exception e){
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  }
