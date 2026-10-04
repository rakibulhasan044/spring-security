package com.example.springBootJwt.controller;


import com.example.springBootJwt.dto.LoginRequestDto;
import com.example.springBootJwt.dto.LoginResponseDto;
import com.example.springBootJwt.service.JwtService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private final ResourceLoader resourceLoader;

    private JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          ResourceLoader resourceLoader,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.resourceLoader = resourceLoader;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto loginRequestDto){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        String token = jwtService.generateToken(authentication);

        return new LoginResponseDto(token);
    }
}
