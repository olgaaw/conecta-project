package com.salesianos.conecta.dto.trabajador;

import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.Trabajador;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateTrabajadorDto(
        @NotEmpty String nombre,
        @NotEmpty String apellidos,
        @NotEmpty String email,
        @NotNull int telefono,
        @NotEmpty String area,
        @NotEmpty String puesto,
        @NotNull Long empresaId

) {
    public Trabajador toTrabajador(Empresa empresa) {
        return Trabajador.builder()
                .nombre(nombre)
                .apellidos(apellidos)
                .email(email)
                .telefono(telefono)
                .area(area)
                .puesto(puesto)
                .empresa(empresa)
                .build();
    }
}
