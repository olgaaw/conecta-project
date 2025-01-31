package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Demanda;

import java.util.Set;
import java.util.stream.Collectors;

public record GetDemandaDto(
        String nombreEmpresa,
        Set<GetFamiliasProfesionalesDto> familiaProfesionalesEmpresa,
        String Curso,
        int cantidadAlumnos
        ){

    public static GetDemandaDto of(Demanda demanda){
        return new GetDemandaDto(
                demanda.getEmpresa().getNombre(),
                demanda.getEmpresa().getFamiliasProfesionales().stream()
                                .map(GetFamiliasProfesionalesDto::of)
                                .collect(Collectors.toSet()),
                demanda.getCurso().getNombre(),
                demanda.getCantidadAlumnos()
                );
    }
}
