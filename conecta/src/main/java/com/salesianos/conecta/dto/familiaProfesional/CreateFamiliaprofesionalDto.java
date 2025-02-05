package com.salesianos.conecta.dto.familiaProfesional;

import com.salesianos.conecta.model.Empresa;

import java.util.Set;

public record CreateFamiliaprofesionalDto(

        String nombre,

        Set<Empresa> empresas
) {
}
