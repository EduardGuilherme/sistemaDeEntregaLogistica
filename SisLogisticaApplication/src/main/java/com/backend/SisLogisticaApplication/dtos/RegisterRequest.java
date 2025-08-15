package com.backend.SisLogisticaApplication.dtos;

import com.backend.SisLogisticaApplication.model.Role;

public record RegisterRequest(String username, String password, Role role) {}
