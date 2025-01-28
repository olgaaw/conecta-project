package com.salesianos.conecta.error;

public class ProfesorNotFoundException extends RuntimeException{
    public ProfesorNotFoundException(Long id) {
        super("No hay profesor con ese ID: %d".formatted(id));
    }

    public ProfesorNotFoundException(String msg) {
        super(msg);
    }

    public ProfesorNotFoundException() {
        super("No hay profesor con esos requisitos de búsqueda");
    }
}
