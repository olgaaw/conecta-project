package com.salesianos.conecta.dto.contacto;

import com.salesianos.conecta.model.Contacto;

import java.time.LocalDate;

public record GetContactoDto (
        LocalDate fecha,
        String canal,
        String resumen,
        String trabajadorNombre,
        String trabajadorEmpresa
) {
    public static GetContactoDto of(Contacto contacto){
        return new GetContactoDto(
                contacto.getFecha(),
                contacto.getCanal(),
                contacto.getResumen(),
                contacto.getTrabajador().getNombre(),
                contacto.getTrabajador().getEmpresa().getNombre()
        );
    }
}
