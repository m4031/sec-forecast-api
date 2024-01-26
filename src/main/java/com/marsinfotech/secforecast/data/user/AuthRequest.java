package com.marsinfotech.secforecast.data.user;

import lombok.Data;

@Data
public class AuthRequest {
    private String grantType;
    private String clientId;
    private String clientSecret;

}
