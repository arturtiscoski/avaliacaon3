package com.example.avaliacaon3.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record CreateRemedioDTO(
        @Size(
                min = 3,
                max = 256,
                message = "O nome deve ter de 3 a 256 caracteres"
        )
        String nome,
        @Size(
                min = 3,
                max = 256,
                message = "A dosagem deve ter de 3 a 256 caracteres"
        )
        @NotBlank(message = "Dosagem não pode ficar em branco")
        @NotNull(message = "Dosagem deve ser incluída")
        String dosagem,
        @Size(
                min = 3,
                max = 256,
                message = "A descrição deve ter de 3 a 256 caracteres"
        )
        @NotBlank(message = "Descrição não pode ficar em branco")
        @NotNull(message = "Descrição deve ser incluída")
        String descricao
) {}

