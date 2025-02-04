package com.salesianos.conecta.dto.empresa;

import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;

import java.util.List;

public record GetEmpresaStringsDto(
        String nombre,
        String direccion,
        List<String> familiasProfesionales,
        int demandas
){

    public static GetEmpresaStringsDto of(Empresa empresa){
        return new GetEmpresaStringsDto(
                empresa.getNombre(),
                empresa.getDireccion(),
                empresa.getFamiliasProfesionales()
                        .stream()
                        .map(FamiliaProfesional::getNombre)
                        .toList(),
                empresa.getDemandas().size()
        );
    }

}
