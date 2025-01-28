package com.salesianos.conecta.service;

import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.repository.CursoRepository;
import com.salesianos.conecta.repository.ProfesorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService {
    private final ProfesorRepository profesorRepository;
    private final CursoRepository cursoRepository;

    public List<Profesor> findAll() {
        List<Profesor> result = profesorRepository.findAll();
        if (result.isEmpty()) {
            throw new EntityNotFoundException("No existen profesores con esos criterios de búsqueda");
        }

        return result;
    }

    public Profesor findById(Long id) {
        return profesorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe ningún profesor con el id"+id));
    }

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(Profesor.builder()
                        .nombre(profesor.getNombre())
                        .apellidos(profesor.getApellidos())
                        .email(profesor.getEmail())
                        .telefono(profesor.getTelefono())
                        .usuario(profesor.getUsuario())
                .build());
    }

    public Profesor edit(Profesor profesor, Long id) {
        return profesorRepository.findById(id)
                .map(old -> {
                    old.setNombre(profesor.getNombre());
                    old.setApellidos(profesor.getApellidos());
                    old.setEmail(profesor.getEmail());
                    old.setTelefono(profesor.getTelefono());
                    old.setUsuario(profesor.getUsuario());

                    return profesorRepository.save(profesor);

                }).orElseThrow(() -> new EntityNotFoundException("No existe profesor con el id"+id));
    }

    public void delete(Long id) {
        profesorRepository.deleteById(id);
    }


}
