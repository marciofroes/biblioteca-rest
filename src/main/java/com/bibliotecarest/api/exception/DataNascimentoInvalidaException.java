package com.bibliotecarest.api.exception;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DataNascimentoInvalidaException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private LocalDate dataNascimento;

}
