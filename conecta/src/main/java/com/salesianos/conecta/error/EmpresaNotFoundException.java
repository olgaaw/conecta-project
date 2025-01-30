package com.salesianos.conecta.error;

public class EmpresaNotFoundException extends RuntimeException {

  public EmpresaNotFoundException(Long id) {
    super("No hay empresa con ese ID: %d".formatted(id));
  }

  public EmpresaNotFoundException(String msg) {
    super(msg);
  }

  public EmpresaNotFoundException() {
    super("No hay empresa con esos requisitos de búsqueda");
  }
}
