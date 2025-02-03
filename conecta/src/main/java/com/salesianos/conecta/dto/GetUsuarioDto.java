package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Usuario;


public record GetUsuarioDto(
        Long id,
        String username,
        String role,
        GetProfesorDto profesor
) {
    public static GetUsuarioDto of (Usuario usuario) {
        return new GetUsuarioDto(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRole(),
                GetProfesorDto.of(usuario.getProfesor())

        );
    }

}
