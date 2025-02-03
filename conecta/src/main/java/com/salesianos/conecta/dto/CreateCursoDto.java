package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.model.Titulo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCursoDto(
        @NotEmpty String nombre,
        @NotNull int horasEmpresa,
        @NotNull Long tituloId
) {
    public Curso toCurso(Titulo titulo) {
        return Curso.builder()
                .nombre(nombre)
                .horasEmpresa(horasEmpresa)
                .titulo(titulo)
                .build();
    }
}
