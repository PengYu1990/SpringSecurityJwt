package com.hugo.jwt.auth.service;

import com.hugo.jwt.auth.repo.UserRepository;
import com.hugo.jwt.auth.domain.Role;
import com.hugo.jwt.auth.domain.User;
import com.hugo.jwt.auth.dto.AuthResponse;
import com.hugo.jwt.auth.dto.LoginRequest;
import com.hugo.jwt.auth.dto.RegisterRequest;
import com.hugo.jwt.security.JwtService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        user.setCtime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user = userRepository.save(user);
        var authResponse = new AuthResponse();
        BeanUtils.copyProperties(user, authResponse);
        String jwtToken = jwtService.generateToken(user);
        authResponse.setToken(jwtToken);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getUsername(),
                        registerRequest.getPassword()
                )
        );
        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(loginRequest.getUsername());
        String jwtToken = jwtService.generateToken(user);
        AuthResponse authResponse = new AuthResponse();
        BeanUtils.copyProperties(user, authResponse);
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}
