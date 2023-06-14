package com.hugo.jwt.auth;

import com.hugo.jwt.auth.dto.AuthResponse;
import com.hugo.jwt.auth.dto.LoginRequest;
import com.hugo.jwt.auth.dto.RegisterRequest;
import com.hugo.jwt.auth.service.AuthService;
import com.hugo.jwt.auth.service.UserService;
import com.hugo.jwt.common.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/signup")
    public R register(@RequestBody RegisterRequest registerRequest){

       AuthResponse authResponse = authService.register(registerRequest);
       return R.success(authResponse);
    }



    @PostMapping("/login")
    public R<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        return R.success(authService.login(loginRequest));

    }


    @PostMapping("/logout")
    public R logout(@RequestParam  String sessionId){

        return R.success(null);
    }
}
