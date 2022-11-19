package com.bibliotecarest.api.utils;

import java.time.LocalDate;

public abstract class Utilitarios {

    public static boolean validarDataNascimento(LocalDate dataNascimento) {
        return !dataNascimento.isAfter(LocalDate.now());
    }
}
