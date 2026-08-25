package com.nicolasgarcia.gestionpersonal.exception;

public class AuthNotFoundExeption extends RuntimeException {
    public AuthNotFoundExeption(String message) {
        super(message);
    }
}
