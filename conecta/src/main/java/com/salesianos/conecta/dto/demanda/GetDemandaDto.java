package com.salesianos.conecta.dto.demanda;

import com.salesianos.conecta.model.Demanda;

public record GetDemandaDto(
        String nombreEmpresa,
        String nombreTitulo,
        String Curso,
        int cantidadAlumnos
        ){

    public static GetDemandaDto of(Demanda demanda){
        return new GetDemandaDto(
                demanda.getEmpresa().getNombre(),
                demanda.getCurso().getTitulo().getNombre(),
                demanda.getCurso().getNombre(),
                demanda.getCantidadAlumnos()
                );
    }
}
