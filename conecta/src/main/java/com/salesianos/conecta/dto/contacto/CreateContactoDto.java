package com.salesianos.conecta.dto.contacto;

import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Trabajador;

import java.util.Date;

public record CreateContactoDto(
        Profesor profesor,
        Trabajador trabajador,
        Date fecha,
        String canal,
        String resumen
) {
}
