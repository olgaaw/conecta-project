package com.salesianos.conecta.error;

public class CursoNotFoundException extends RuntimeException {

  public CursoNotFoundException(Long id) {
    super("No hay curso con ese ID: %d".formatted(id));
  }

  public CursoNotFoundException(String msg) {
    super(msg);
  }

  public CursoNotFoundException() {
    super("No hay curso con esos requisitos de búsqueda");
  }
}
