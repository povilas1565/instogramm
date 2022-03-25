package com.example.demo.payload.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTSuccessResponse {
    private boolean success;
    private String token;
    }

