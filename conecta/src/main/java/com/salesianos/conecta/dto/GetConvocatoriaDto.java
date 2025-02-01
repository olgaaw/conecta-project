package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record GetConvocatoriaDto(
        String cursoEscolar,
        String nombre,
        int numeroDemandas
        ){

    public static GetConvocatoriaDto of(Convocatoria convocatoria){
        return new GetConvocatoriaDto(
                convocatoria.getCursoEscolar(),
                convocatoria.getNombre(),
                convocatoria.getDemandas().size()
                );
    }
}
