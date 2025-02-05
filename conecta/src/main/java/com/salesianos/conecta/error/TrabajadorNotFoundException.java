package com.salesianos.conecta.error;

public class TrabajadorNotFoundException extends RuntimeException {
  public TrabajadorNotFoundException(Long id) {
    super("No hay trabajador con ese ID: %d".formatted(id));
  }

  public TrabajadorNotFoundException(String msg) {
    super(msg);
  }

  public TrabajadorNotFoundException() {
    super("No hay trabajador con esos requisitos de búsqueda");
  }
}
