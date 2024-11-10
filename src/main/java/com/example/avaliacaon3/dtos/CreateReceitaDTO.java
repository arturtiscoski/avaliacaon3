package com.example.avaliacaon3.dtos;

import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.models.Remedio;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.UUID;

public record CreateReceitaDTO(
        @Size(
                min = 3,
                max = 256,
                message = "A posologia deve ter entre 3 e 256 caracteres"
        )
        @NotBlank(message = "Posologia não pode ser vazio")
        @NotNull(message = "Posologia não pode ser vazio")
        String posologia,
        @NotNull(message = "Paciente não pode ser vazio")
        UUID pacienteUUID,
        @NotEmpty(message = "Remédios não podem estar vazios")
        List<UUID> remediosUUID
) {}

