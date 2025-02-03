package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.EditCursoCmd;
import com.salesianos.conecta.error.ContactoNotFoundException;
import com.salesianos.conecta.error.CursoNotFoundException;
import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    public List<Curso> findAll() {
        List<Curso> result = cursoRepository.findAll();
        if (result.isEmpty()) {
            throw new CursoNotFoundException();
        }
        return result;
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new ContactoNotFoundException(id));
    }

    public Curso edit(EditCursoCmd curso, Long id) {
        return cursoRepository.findById(id)
                .map(old -> {
                    old.setNombre(curso.nombre());
                    old.setHorasEmpresa(curso.horasEmpresa());
                    old.setTitulo(curso.titulo());

                    return cursoRepository.save(old);
                }).orElseThrow(() -> new CursoNotFoundException(id));
    }

}
