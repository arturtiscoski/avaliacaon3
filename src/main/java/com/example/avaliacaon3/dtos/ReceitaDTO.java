package com.example.avaliacaon3.dtos;

import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.models.Remedio;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ReceitaDTO (
    UUID id,
    String posologia,
    Date dataPrescricao,
    Paciente paciente,
    List<Remedio> remedios
) {}
