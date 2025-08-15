package com.backend.SisLogisticaApplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoPaletizacaoDTO(
        Long id,
        @NotBlank @Size(max = 50) String nome
) {}

