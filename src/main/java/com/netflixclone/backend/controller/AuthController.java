package com.netflixclone.backend.controller;

import com.netflixclone.backend.dto.AuthResponse;
import com.netflixclone.backend.dto.LoginRequest;
import com.netflixclone.backend.dto.RegisterRequest;
import com.netflixclone.backend.dto.UpdatePlanRequest;
import com.netflixclone.backend.model.User;
import com.netflixclone.backend.service.SubscriptionPlanService;
import com.netflixclone.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "*"})
public class AuthController {

    private final UserService userService;
    private final SubscriptionPlanService planService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = userService.register(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = userService.login(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/update-plan")
    public ResponseEntity<AuthResponse> updatePlan(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdatePlanRequest request) {
        try {
            String email = userService.extractEmailFromToken(token.replace("Bearer ", ""));
            Optional<User> userOpt = userService.getUserByEmail(email);

            if (userOpt.isEmpty()) {
                AuthResponse response = new AuthResponse();
                response.setSuccess(false);
                response.setMessage("User not found");
                return ResponseEntity.badRequest().body(response);
            }

            User updatedUser = planService.assignPlanToUser(userOpt.get().getId(), request.getPlanId());

            AuthResponse response = new AuthResponse();
            response.setUserId(updatedUser.getId());
            response.setEmail(updatedUser.getEmail());
            response.setName(updatedUser.getName());
            response.setPlan(updatedUser.getPlan());
            response.setSuccess(true);
            response.setMessage("Plan updated successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AuthResponse response = new AuthResponse();
            response.setSuccess(false);
            response.setMessage("Failed to update plan: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }
}
