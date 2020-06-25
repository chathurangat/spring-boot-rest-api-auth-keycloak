package com.springbootdev.examples.service;

import lombok.*;

@Data
public class TokenRequest {

    private String clientId;
    private String grantType;
    private String clientSecret;
}
