package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.model.FamiliaProfesional;
import com.salesianos.conecta.model.Titulo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateTituloDto(
        @NotEmpty String nombre,
        @NotNull int duracion,
        @NotEmpty String grado,
        @NotNull FamiliaProfesional familiaProfesional,
        @NotEmpty List<Curso> curso
) {

    public Titulo toTitulo(FamiliaProfesional familiaProfesional) {
        return Titulo.builder()
                .nombre(nombre)
                .duracion(duracion)
                .grado(grado)
                .familiaProfesional(familiaProfesional)
                .cursos(curso)
                .build();
    }
}
