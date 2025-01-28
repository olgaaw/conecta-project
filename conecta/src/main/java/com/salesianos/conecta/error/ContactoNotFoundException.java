package com.salesianos.conecta.error;

public class ContactoNotFoundException extends RuntimeException {
    public ContactoNotFoundException(Long id) {
        super("No hay contacto con ese ID: %d".formatted(id));
    }

    public ContactoNotFoundException(String msg) {
        super(msg);
    }

    public ContactoNotFoundException() {
        super("No hay contacto con esos requisitos de búsqueda");
    }
}
