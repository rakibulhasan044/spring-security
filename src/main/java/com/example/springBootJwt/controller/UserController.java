package com.example.springBootJwt.controller;

import com.example.springBootJwt.dto.UserRegisterRequestDto;
import com.example.springBootJwt.dto.UserRegisterResponseDto;
import com.example.springBootJwt.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String sayHello(Authentication authentication) {
        return "Hello you are logged in as : " + authentication.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(
            @RequestBody UserRegisterRequestDto registerRequestDto) {
        UserRegisterResponseDto userRegisterResponseDto
                = authService.register(registerRequestDto);

        return ResponseEntity.ok(userRegisterResponseDto);
    }


    @GetMapping("/token")
    public CsrfToken getToken(CsrfToken csrfToken) {
        return csrfToken;
    }

}
