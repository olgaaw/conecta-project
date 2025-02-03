package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record CreateEmpresaDto(
        String nombre,

        String direccion,

        Set<FamiliaProfesional> familiasProfesionales,

        List<Demanda> demandas
) {
}
