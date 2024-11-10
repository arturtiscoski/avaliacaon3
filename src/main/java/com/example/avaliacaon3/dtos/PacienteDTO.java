package com.example.avaliacaon3.dtos;

import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.models.Remedio;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record PacienteDTO(
    UUID id,
    String nome,
    String cpf,
    Integer idade
) {}
