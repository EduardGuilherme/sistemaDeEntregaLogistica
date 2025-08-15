package com.backend.SisLogisticaApplication.dtos;

public record AuthResponse(String accessToken, String tokenType, long expiresInMs) {}
