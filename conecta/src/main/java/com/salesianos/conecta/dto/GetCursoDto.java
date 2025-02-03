package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.model.Profesor;

import java.util.Set;
import java.util.stream.Collectors;

public record GetCursoDto(
        Long id,
        String nombre,
        int horasEmpresa,
        Set<GetProfesorDto> profesores,
        String nombreTitulo
) {
    public static GetCursoDto of (Curso curso) {
        return new GetCursoDto(
                curso.getId(),
                curso.getNombre(),
                curso.getHorasEmpresa(),
                curso.getProfesores()
                        .stream()
                        .map(GetProfesorDto::of)
                        .collect(Collectors.toSet()),
                curso.getTitulo().getNombre()
        );
    }
}
