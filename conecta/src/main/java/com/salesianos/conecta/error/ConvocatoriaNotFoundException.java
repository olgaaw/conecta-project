package com.salesianos.conecta.error;

public class ConvocatoriaNotFoundException extends RuntimeException {
  public ConvocatoriaNotFoundException(Long id) {
    super("No hay convocatoria con ese ID: %d".formatted(id));
  }

  public ConvocatoriaNotFoundException(String msg) {
    super(msg);
  }

  public ConvocatoriaNotFoundException() {
    super("No hay convocatoria con esos requisitos de búsqueda");
  }
}