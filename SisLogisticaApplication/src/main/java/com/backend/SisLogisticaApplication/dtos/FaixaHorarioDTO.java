package com.backend.SisLogisticaApplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FaixaHorarioDTO(
        Long id,
        @NotBlank @Size(max = 50) String descricao
) {}
