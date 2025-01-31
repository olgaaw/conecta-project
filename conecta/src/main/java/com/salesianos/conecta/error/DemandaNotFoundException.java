package com.salesianos.conecta.error;

public class DemandaNotFoundException extends RuntimeException {
  public DemandaNotFoundException(Long id) {
    super("No hay demanda con ese ID: %d".formatted(id));
  }

  public DemandaNotFoundException(String msg) {
    super(msg);
  }

  public DemandaNotFoundException() {
    super("No hay demanda con esos requisitos de búsqueda");
  }
}