package com.salesianos.conecta.error;

import com.salesianos.conecta.model.FamiliaProfesional;
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

    @ExceptionHandler(DemandaNotFoundException.class)
    public ProblemDetail handleDemandaNotFound(DemandaNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Demanda no encontrada");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/demanda-not-found"));
        result.setProperty("author", "David");

        return result;

    }

    @ExceptionHandler(ConvocatoriaNotFoundException.class)
    public ProblemDetail handleConvocatoriaNotFound(ConvocatoriaNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Convocatoria no encontrada");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/convocatoria-not-found"));
        result.setProperty("author", "David");

        return result;

    }

    @ExceptionHandler(FamiliaProfesionalNotFoundException.class)
    public ProblemDetail handleFamiliaProfesionalNotFound(FamiliaProfesionalNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Familia Profesional no encontrada");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/familiaProfesional-not-found"));
        result.setProperty("author", "David");

        return result;

    }

    @ExceptionHandler(CursoNotFoundException.class)
    public ProblemDetail handleCursoNotFound(CursoNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Curso no encontrado");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/curso-not-found"));
        result.setProperty("author", "Olga");

        return result;

    }

    @ExceptionHandler(TituloNotFoundException.class)
    public ProblemDetail handleTituloNotFound(TituloNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Titulo no encontrado");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/titulo-not-found"));
        result.setProperty("author", "Olga");

        return result;

    }

    @ExceptionHandler(TrabajadorNotFoundException.class)
    public ProblemDetail handleTrabajadorNotFound(TrabajadorNotFoundException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                        ex.getMessage());
        result.setTitle("Trabajador no encontrado");
        result.setType(URI.create("https://www.salesianos-triana.edu/errors/trabajador-not-found"));
        result.setProperty("author", "Olga");

        return result;

    }






}



