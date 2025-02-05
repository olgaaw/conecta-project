package com.salesianos.conecta.dto.empresa;

import com.salesianos.conecta.model.*;

import java.util.List;
import java.util.Set;

public record CreateEmpresaDto(
        String nombre,

        String direccion,

        Set<FamiliaProfesional> familiasProfesionales,

        List<Demanda> demandas
) {
}
