package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.Empresa;

import java.util.List;
import java.util.Set;

public record CreateFamiliaprofesionalDto(

        String nombre,

        Set<Empresa> empresas
) {
}
