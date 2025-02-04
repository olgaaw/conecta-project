package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Titulo;

public record GetTituloDto(
        String nombre,
        int duracion,
        String grado,
        GetFamiliasProfesionalesDto familiasProfesionalesDto
) {
    public static GetTituloDto of (Titulo titulo) {
        return new GetTituloDto(
                titulo.getNombre(),
                titulo.getDuracion(),
                titulo.getGrado(),
                GetFamiliasProfesionalesDto.of(titulo.getFamiliaProfesional())

        );
    }
}
