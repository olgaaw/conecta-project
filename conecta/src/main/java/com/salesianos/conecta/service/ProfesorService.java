package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.profesor.EditProfesorCmd;
import com.salesianos.conecta.error.ProfesorNotFoundException;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public List<Profesor> findAll() {
        List<Profesor> result = profesorRepository.findAll();
        if (result.isEmpty()) {
            throw new ProfesorNotFoundException();
        }
        return result;
    }

    public Profesor findById(Long id) {
        return profesorRepository.findById(id).orElseThrow(() -> new ProfesorNotFoundException(id));
    }

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(Profesor.builder()
                .nombre(profesor.getNombre())
                .apellidos(profesor.getApellidos())
                .email(profesor.getEmail())
                .telefono(profesor.getTelefono())
                .build());
    }

    public Profesor edit(EditProfesorCmd profesor, Long id) {
        return profesorRepository.findById(id)
                .map(old -> {
                    old.setNombre(profesor.nombre());
                    old.setApellidos(profesor.apellidos());
                    old.setEmail(profesor.email());
                    old.setTelefono(profesor.telefono());
                    return profesorRepository.save(old);
                }).orElseThrow(() -> new ProfesorNotFoundException(id));
    }

    public void delete(Long id) {
        profesorRepository.deleteById(id);
    }
}