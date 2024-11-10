package com.example.avaliacaon3.exceptions;

import org.springframework.http.HttpStatus;

public class RequisicaoErradaException extends ServiceException{
    public RequisicaoErradaException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
