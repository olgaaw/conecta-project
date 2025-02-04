package com.salesianos.conecta.dto.usuario;

import com.salesianos.conecta.dto.profesor.GetProfesorDto;
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
