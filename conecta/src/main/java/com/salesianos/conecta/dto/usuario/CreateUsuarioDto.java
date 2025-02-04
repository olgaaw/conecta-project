package com.salesianos.conecta.dto.usuario;

import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record CreateUsuarioDto(
        @NotNull(message = "El profesor no puede ser nulo")
        Profesor profesor,
        @NotNull(message = "El username no puede ser nulo")
        String username,
        @NotNull(message = "La contraseña no puede ser nula")
        String password

) {


    public Usuario toUsuario() {
        return Usuario.builder()
                .username(this.username)
                .password(this.password)
                .role("profesor")
                .profesor(this.profesor)
                .build();
    }
}
