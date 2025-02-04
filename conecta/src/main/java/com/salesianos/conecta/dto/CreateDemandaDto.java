package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.Titulo;

public record CreateDemandaDto(
        Empresa empresa,

        Curso curso,

        int cantidadAlumnos
) {
}
