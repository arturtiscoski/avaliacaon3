package com.example.avaliacaon3.exceptions;

import com.example.avaliacaon3.dtos.ErroDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroDTO handleNotFoundException(NaoEncontradoException e) {
        return new ErroDTO(e.getTimestamp(), e.getStatus(), e.getMensagem());
    }

    @ExceptionHandler(ConflitoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErroDTO handleConflictException(ConflitoException e) {
        return new ErroDTO(e.getTimestamp(), e.getStatus(), e.getMensagem());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDTO handleConflictException(BadRequestException e) {
        return new ErroDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDTO handleValidationException(MethodArgumentNotValidException e) {
        return new ErroDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST, e.getAllErrors().get(0).getDefaultMessage());
    }
}
