package com.hugo.jwt.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    private Long id;

    private String username;
    private String password;
    private String email;
    private String profile;
}
