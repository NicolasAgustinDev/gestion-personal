package com.nicolasgarcia.gestionpersonal.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{
    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ErrorResponseDTO crearError(
            HttpStatus status,
            String mensaje,
            HttpServletRequest request,
            Map<String, String> errores) {
        return new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensaje,
                request.getRequestURI(),
                errores
        );
    }


    @ExceptionHandler(ResourceNotFoundExcepcion.class)
    public ResponseEntity<ErrorResponseDTO> recursoNoEncontrado(
            ResourceNotFoundExcepcion ex,
            HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(crearError(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        request,
                        null
                ));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> recursoYaExistente(
            ResourceAlreadyExistsException ex,
            HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity
                .status(status)
                .body(crearError(
                        status,
                        ex.getMessage(),
                        request,
                        null
                ));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarErroresValidacion(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() != null
                                ? error.getDefaultMessage()
                                : "Valor inválido",
                        (anterior, nuevo) -> anterior
                ));

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(crearError(
                status,
                "Error de validacion de los datos enviados",
                request,
                errores
        ));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> erroresNegocio(
            BusinessException ex,
            HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(crearError(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        request,
                        null
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> manejarErrorInesperado(
            Exception ex,
            HttpServletRequest request) {

        log.error("Error inesperado en {}", request.getRequestURI(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(crearError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Ocurrió un error interno en el servidor",
                        request,
                        null
                ));
    }
}

