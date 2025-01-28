package com.salesianos.conecta.error;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(Long id) {
        super("No hay usuario con ese ID: %d".formatted(id));
    }

    public UsuarioNotFoundException(String msg) {
        super(msg);
    }

    public UsuarioNotFoundException() {
        super("No hay usuario con esos requisitos de búsqueda");
    }
}
