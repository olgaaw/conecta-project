package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record GetEmpresaDto (
        String nombre,
        String direccion,
        Set<GetFamiliasProfesionalesDto> familiaProfesionales,
        int demandas
){

    public static GetEmpresaDto of(Empresa empresa){
        return new GetEmpresaDto(
                empresa.getNombre(),
                empresa.getDireccion(),
                empresa.getFamiliasProfesionales().stream()
                                .map(GetFamiliasProfesionalesDto::of)
                                .collect(Collectors.toSet()),
                empresa.getDemandas().size()
        );
    }
}
