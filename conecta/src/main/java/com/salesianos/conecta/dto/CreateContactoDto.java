package com.salesianos.conecta.dto;

import java.util.Date;

public record CreateContactoDto(
        Long profesorId,
        Long trabajadorId,
        Date fecha,
        String canal,
        String resumen
) {
}
