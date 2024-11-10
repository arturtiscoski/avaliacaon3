package com.example.avaliacaon3.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CreatePacienteDTO(
        @Min(0)
        @Max(120)
        int idade,
        @Size(
                min = 3,
                max = 256,
                message = "O nome deve ter de 3 a 256 caracteres"
        )
        String nome,
        @CPF(message = "CPF inválido!")
        String cpf
) {}

