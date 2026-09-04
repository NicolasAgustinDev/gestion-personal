package com.nicolasgarcia.gestionpersonal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(EmployeesNotFoundExeption.class)
    public ResponseEntity<String> empleadoNoEncontrado(
            EmployeesNotFoundExeption ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(CategoriaNotFoundExeption.class)
    public ResponseEntity<String> categoriaNoEncontrado(CategoriaNotFoundExeption ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());

    }
    @ExceptionHandler(UsuarioNotFoundExeption.class)
    public ResponseEntity<String> UsuarioNoEncontrado(UsuarioNotFoundExeption ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());

    }
}

