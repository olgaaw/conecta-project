package com.salesianos.conecta.dto.profesor;

import lombok.Builder;

@Builder
public record EditProfesorCmd(
        String nombre,
        String apellidos,
        String email,
        int telefono
) {

}
