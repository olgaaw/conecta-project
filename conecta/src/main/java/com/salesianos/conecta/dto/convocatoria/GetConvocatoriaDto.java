package com.salesianos.conecta.dto.convocatoria;

import com.salesianos.conecta.model.Convocatoria;

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