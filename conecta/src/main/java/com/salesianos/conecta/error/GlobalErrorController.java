package com.salesianos.conecta.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorController
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContactoNotFoundException.class)
    public ProblemDetail handleContactoNotFound(ContactoNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("contacto no encontrado");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/contacto-not-found"));
        result.setProperty("author", "David");

        return result;

    }
    @ExceptionHandler(ProfesorNotFoundException.class)
    public ProblemDetail handleProfesorNotFound(ProfesorNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Profesor no encontrado");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/profesor-not-found"));
        result.setProperty("author", "Olga");

        return result;

    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ProblemDetail handleUsuarioNotFound(UsuarioNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Usuario no encontrado");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/usuario-not-found"));
        result.setProperty("author", "Olga");

        return result;

    }

    @ExceptionHandler(EmpresaNotFoundException.class)
    public ProblemDetail handleEmpresaNotFound(EmpresaNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Empresa no encontrada");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/empresa-not-found"));
        result.setProperty("author", "David");

        return result;

    }



}


