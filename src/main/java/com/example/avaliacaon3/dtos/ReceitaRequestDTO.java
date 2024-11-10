package com.example.avaliacaon3.dtos;

import java.util.List;
import java.util.UUID;

public record ReceitaRequestDTO(
    String posologia,
    UUID pacienteUUID,
    List<UUID> remediosUUID
) {}
