package com.salesianos.conecta.dto.trabajador;

import com.salesianos.conecta.dto.empresa.GetEmpresaDto;
import com.salesianos.conecta.model.Trabajador;

public record GetTrabajadorDto(
        String nombre,
        String apellidos,
        String email,
        int telefono,
        String area,
        String puesto,
        GetEmpresaDto empresaDto
) {

    public static GetTrabajadorDto of(Trabajador trabajador) {
        return new GetTrabajadorDto(
                trabajador.getNombre(),
                trabajador.getApellidos(),
                trabajador.getEmail(),
                trabajador.getTelefono(),
                trabajador.getArea(),
                trabajador.getPuesto(),
                GetEmpresaDto.of(trabajador.getEmpresa())
        );
    }
}
