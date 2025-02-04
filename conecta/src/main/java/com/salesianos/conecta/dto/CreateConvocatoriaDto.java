package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.FamiliaProfesional;

import java.util.List;
import java.util.Set;

public record CreateConvocatoriaDto(
        String cursoEscolar,

        String nombre,

        List<Demanda> demandas
) {
}
