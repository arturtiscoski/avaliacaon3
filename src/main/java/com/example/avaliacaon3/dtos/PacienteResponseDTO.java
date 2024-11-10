package com.example.avaliacaon3.dtos;

import java.util.UUID;

public record PacienteResponseDTO(
        UUID id,
        String nome,
        String cpf,
        int idade
) {}
