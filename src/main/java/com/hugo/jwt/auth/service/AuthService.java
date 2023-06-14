package com.hugo.jwt.auth.service;

import com.hugo.jwt.auth.dto.AuthResponse;
import com.hugo.jwt.auth.dto.LoginRequest;
import com.hugo.jwt.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
