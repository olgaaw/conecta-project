package com.salesianos.conecta.dto.familiaProfesional;

import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;

import java.util.List;

public record GetFamiliasProfesionalesDemandasDto (
        String nombreFamiliaProfesional,
        List<String> nombreEmpresas
){
    public static GetFamiliasProfesionalesDemandasDto of(FamiliaProfesional familiaProfesional){
        return new GetFamiliasProfesionalesDemandasDto(
                familiaProfesional.getNombre(),
                familiaProfesional.getEmpresas()
                        .stream()
                        .map(Empresa::getNombre)
                        .toList()
                );
    }
}
