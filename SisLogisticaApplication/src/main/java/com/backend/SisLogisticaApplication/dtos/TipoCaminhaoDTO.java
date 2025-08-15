package com.backend.SisLogisticaApplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoCaminhaoDTO(
        Long id,
        @NotBlank @Size(max = 50) String nome
) {}

