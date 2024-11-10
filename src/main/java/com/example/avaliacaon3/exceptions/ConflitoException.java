package com.example.avaliacaon3.exceptions;

import org.springframework.http.HttpStatus;

public class ConflitoException extends ServiceException {
    public ConflitoException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
