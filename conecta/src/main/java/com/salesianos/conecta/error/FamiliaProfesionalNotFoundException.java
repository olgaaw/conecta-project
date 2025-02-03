package com.salesianos.conecta.error;

public class FamiliaProfesionalNotFoundException extends RuntimeException {
  public FamiliaProfesionalNotFoundException(Long id) {
    super("No hay familia profesional con ese ID: %d".formatted(id));
  }

  public FamiliaProfesionalNotFoundException(String msg) {
    super(msg);
  }

  public FamiliaProfesionalNotFoundException() {
    super("No hay familia profesional con esos requisitos de búsqueda");
  }
}