package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Titulo;

public record EditCursoCmd(
        String nombre,
        int horasEmpresa,
        Titulo titulo
) {
}
