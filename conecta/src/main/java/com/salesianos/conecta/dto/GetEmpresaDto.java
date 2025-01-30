package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;

import java.util.List;
import java.util.Set;

public record GetEmpresaDto (
        String nombre,
        String direccion,
        Set<FamiliaProfesional> familiaProfesionales,
        int demandas
){

    public static GetEmpresaDto of(Empresa empresa){
        return new GetEmpresaDto(
                empresa.getNombre(),
                empresa.getDireccion(),
                empresa.getFamiliasProfesionales(),
                empresa.getDemandas().size()
        );
    }
}
