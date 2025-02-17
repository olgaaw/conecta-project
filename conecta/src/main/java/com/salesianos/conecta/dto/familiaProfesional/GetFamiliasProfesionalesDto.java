package com.salesianos.conecta.dto.familiaProfesional;

import com.salesianos.conecta.model.FamiliaProfesional;

public record GetFamiliasProfesionalesDto(
    String nombre
            ){

    public static GetFamiliasProfesionalesDto of(FamiliaProfesional familiaProfesional){
        return new GetFamiliasProfesionalesDto(
                familiaProfesional.getNombre());

    }

}
