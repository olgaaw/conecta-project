package com.salesianos.conecta.error;

public class TituloNotFoundException extends RuntimeException {
  public TituloNotFoundException(Long id) {
    super("No hay curso con ese ID: %d".formatted(id));
  }

  public TituloNotFoundException(String msg) {
    super(msg);
  }

  public TituloNotFoundException() {
    super("No hay curso con esos requisitos de búsqueda");
  }
}
