package com.example.avaliacaon3.dtos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ErroDTO(
        LocalDateTime timestamp,
        HttpStatus erro,
        String mensagem
) {}
