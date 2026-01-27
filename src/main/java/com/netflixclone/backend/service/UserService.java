package com.netflixclone.backend.service;

import com.netflixclone.backend.dto.AuthResponse;
import com.netflixclone.backend.dto.LoginRequest;
import com.netflixclone.backend.dto.RegisterRequest;
import com.netflixclone.backend.model.User;
import com.netflixclone.backend.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.jwt.secret:mySecretKeyForNetflixCloneProjectPleaseChangeInProduction}")
    private String jwtSecret;

    @Value("${app.jwt.expiration:86400000}")
    private Long jwtExpiration; // 24 hours in milliseconds

    public AuthResponse register(RegisterRequest request) {
        AuthResponse response = new AuthResponse();

        // Validate input
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            response.setSuccess(false);
            response.setMessage("Passwords do not match");
            return response;
        }

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Email is required");
            return response;
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            response.setSuccess(false);
            response.setMessage("Email already registered");
            return response;
        }

        try {
            // Create new user
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setName(request.getName());
            user.setIsActive(true);

            User savedUser = userRepository.save(user);

            // Generate token
            String token = generateToken(savedUser);

            response.setUserId(savedUser.getId());
            response.setEmail(savedUser.getEmail());
            response.setName(savedUser.getName());
            response.setToken(token);
            response.setSuccess(true);
            response.setMessage("Registration successful");

            return response;
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Registration failed: " + e.getMessage());
            return response;
        }
    }

    public AuthResponse login(LoginRequest request) {
        AuthResponse response = new AuthResponse();

        try {
            Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

            if (userOpt.isEmpty()) {
                response.setSuccess(false);
                response.setMessage("User not found");
                return response;
            }

            User user = userOpt.get();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                response.setSuccess(false);
                response.setMessage("Invalid password");
                return response;
            }

            // Generate token
            String token = generateToken(user);

            response.setUserId(user.getId());
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setPlan(user.getPlan());
            response.setToken(token);
            response.setSuccess(true);
            response.setMessage("Login successful");

            return response;
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Login failed: " + e.getMessage());
            return response;
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUserPlan(Long userId, Long planId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Plan will be set by the controller using SubscriptionPlanService
            return userRepository.save(user);
        }
        return null;
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmailFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
