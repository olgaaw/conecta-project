package com.salesianos.conecta.dto.convocatoria;

import com.salesianos.conecta.model.Demanda;

import java.util.List;

public record CreateConvocatoriaDto(
        String cursoEscolar,

        String nombre,

        List<Demanda> demandas
) {
}
