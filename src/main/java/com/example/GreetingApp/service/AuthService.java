package com.example.GreetingApp.service;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.LoginDTO;
import com.example.GreetingApp.exception.EmailAlreadyExistsException;
import com.example.GreetingApp.exception.InvalidLoginException;
import com.example.GreetingApp.model.AuthUser;
import com.example.GreetingApp.repository.AuthUserRepository;
import com.example.GreetingApp.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    @Autowired
    public AuthService(AuthUserRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,EmailService emailService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    public String registerUser(AuthUserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + dto.getEmail());
        }

        AuthUser user = new AuthUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        repository.save(user);
        //Email sent when successfull registration
        emailService.sendWelcomeEmail(user.getEmail(), user.getFirstName());

        return "User registered successfully! Welcome email sent.";
    }

    public String loginUser(LoginDTO dto) {
        AuthUser user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidLoginException("Invalid email or password!"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidLoginException("Invalid email or password!");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}