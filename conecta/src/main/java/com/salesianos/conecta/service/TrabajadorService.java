package com.salesianos.conecta.service;

import com.salesianos.conecta.model.Trabajador;
import com.salesianos.conecta.repository.EmpresaRepository;
import com.salesianos.conecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrabajadorService {
    private final TrabajadorRepository trabajadorRepository;
    private final EmpresaRepository empresaRepository;

    public List<Trabajador> findAll() {
        List<Trabajador> result = trabajadorRepository.findAll();
        if (result.isEmpty()) {
            throw new EntityNotFoundException("No existe ningun trabajador con esos criterios de búsqueda");
        }
        return result;
    }

    public Trabajador findById(Long id) {
       return trabajadorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe ningun trabajador con esps criterios de búsqueda"));
    }

    public Trabajador save(Trabajador trabajador) {
        return trabajadorRepository.save(Trabajador.builder()
                        .nombre(trabajador.getNombre())
                        .apellidos(trabajador.getApellidos())
                        .email(trabajador.getEmail())
                        .telefono(trabajador.getTelefono())
                        .area(trabajador.getArea())
                        .puesto(trabajador.getPuesto())
                .build());
    }

    public Trabajador edit(Trabajador trabajador, Long id) {
        return trabajadorRepository.findById(id)
                .map(old -> {
                    old.setNombre(trabajador.getNombre());
                    old.setApellidos(trabajador.getApellidos());
                    old.setEmail(trabajador.getEmail());
                    old.setTelefono(trabajador.getTelefono());
                    old.setArea(trabajador.getArea());
                    old.setPuesto(trabajador.getPuesto());
                    return trabajadorRepository.save(trabajador);
                }).orElseThrow(() -> new EntityNotFoundException("No existe ningún trabajador con el id "+id));
    }

    public void delete(Long id) {
        trabajadorRepository.deleteById(id);
    }




}
