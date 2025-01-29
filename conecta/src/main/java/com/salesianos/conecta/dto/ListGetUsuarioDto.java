package com.salesianos.conecta.dto;

import com.salesianos.conecta.model.Usuario;

import java.util.List;

public record ListGetUsuarioDto(
        long count,
        List<GetUsuarioDto> items
) {
    public static ListGetUsuarioDto of(List<Usuario> list) {
        return new ListGetUsuarioDto(
                list.size(),
                list.stream()
                        .map(GetUsuarioDto::of)
                        .toList()
        );
    }
}
