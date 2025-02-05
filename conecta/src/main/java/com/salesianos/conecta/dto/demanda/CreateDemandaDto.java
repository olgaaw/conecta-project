package com.salesianos.conecta.dto.demanda;

import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.model.Empresa;

public record CreateDemandaDto(
        Empresa empresa,

        Curso curso,

        int cantidadAlumnos
) {
}
