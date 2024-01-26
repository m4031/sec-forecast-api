package com.marsinfotech.secforecast.data.user;

import lombok.Data;

@Data
public class AuthResponse {
    private String tokenType;
    private String accessToken;
    private long expiresInSecs;
}
