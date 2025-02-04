package com.salesianos.conecta.dto.convocatoria;

import com.salesianos.conecta.model.Demanda;

import java.util.Set;

public record CreateConvocatoriaDto(
        String cursoEscolar,

        String nombre,

        Set<Demanda> demandas
) {
}
