package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Profesor;

public record GetProfesorDto(
        Long id,
        String nombre,
        String apellidos,
        String email,
        int telefono
) {
    public static GetProfesorDto of (Profesor profesor) {
        return new GetProfesorDto(
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getEmail(),
                profesor.getTelefono()

        );
    }
}
