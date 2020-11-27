package io.work.confino.services;

import io.work.confino.security.request.LoginRequest;
import io.work.confino.security.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signupRequest);
}
