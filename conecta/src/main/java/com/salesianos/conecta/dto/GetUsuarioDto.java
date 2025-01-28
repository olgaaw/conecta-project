package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Usuario;

import java.util.List;

public record GetUsuarioDto(
        Long id,
        String username,
        String role,
        String nombreProfesor,
        String apellidosProfesor
) {
    public static GetUsuarioDto of (Usuario usuario) {
        return new GetUsuarioDto(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRole(),
                usuario.getProfesor().getNombre(),
                usuario.getProfesor().getApellidos()
        );
    }

}
