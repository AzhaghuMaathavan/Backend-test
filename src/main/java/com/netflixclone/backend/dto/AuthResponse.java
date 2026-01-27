package com.netflixclone.backend.dto;

import com.netflixclone.backend.model.SubscriptionPlan;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String email;
    private String name;
    private String token;
    private SubscriptionPlan plan;
    private String message;
    private boolean success;
}
