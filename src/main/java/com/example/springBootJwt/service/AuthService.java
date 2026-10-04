package com.example.springBootJwt.service;

import com.example.springBootJwt.dto.UserRegisterRequestDto;
import com.example.springBootJwt.dto.UserRegisterResponseDto;
import com.example.springBootJwt.entity.Role;
import com.example.springBootJwt.entity.User;
import com.example.springBootJwt.repository.RoleRepository;
import com.example.springBootJwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDto register(
            UserRegisterRequestDto registerRequestDto) {

        User user = new User();

        user.setUsername(registerRequestDto.getUsername());

        String encodedPassword =
                passwordEncoder.encode(registerRequestDto.getPassword());

        user.setPassword(encodedPassword);
        user.setEnabled(true);

//        Role role = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
//            Role newRole = new Role();
//            newRole.setName("ROLE_USER");
//            return roleRepository.save(newRole);
//        });

        Role role = roleRepository.findByName("ROLE_USER").get();

        user.getRoles().add(role);


        userRepository.save(user);

        UserRegisterResponseDto responseDto = new
                UserRegisterResponseDto();

        responseDto.setUsername(user.getUsername());
        responseDto.setMessage("User saved Successfully");

        return responseDto;

    }
}
