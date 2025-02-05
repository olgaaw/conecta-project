package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.curso.EditCursoCmd;
import com.salesianos.conecta.dto.curso.CreateCursoDto;
import com.salesianos.conecta.dto.curso.GetCursoDto;
import com.salesianos.conecta.error.CursoNotFoundException;
import com.salesianos.conecta.error.TituloNotFoundException;
import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Titulo;
import com.salesianos.conecta.repository.CursoRepository;
import com.salesianos.conecta.repository.TituloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;
    private final TituloRepository tituloRepository;

    public List<Curso> findAll() {
        List<Curso> result = cursoRepository.findAll();
        if (result.isEmpty()) {
            throw new CursoNotFoundException();
        }
        return result;
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException());
    }

    public Curso edit(EditCursoCmd curso, Long id) {
        return cursoRepository.findById(id)
                .map(old -> {
                    if (curso.nombre() != null) {
                        old.setNombre(curso.nombre());
                    }
                    old.setHorasEmpresa(curso.horasEmpresa());
                    if (curso.titulo() != null) {
                        old.setTitulo(curso.titulo());
                    }
                    return cursoRepository.save(old);
                })
                .orElseThrow(() -> new CursoNotFoundException(id));
    }


    public Curso save(CreateCursoDto dto) {
        Titulo titulo = tituloRepository.findById(dto.tituloId())
                .orElseThrow(() -> new TituloNotFoundException(dto.tituloId()));

        return cursoRepository.save(dto.toCurso(titulo));
    }

    public void delete(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException(id));

        for (Profesor profesor : curso.getProfesores()) {
            profesor.getCursos().remove(curso);
        }

        if (curso.getTitulo() != null) {
            curso.getTitulo().getCursos().remove(curso);
        }
        cursoRepository.deleteDemandasByCursoId(id);

        cursoRepository.delete(curso);
    }

    public List<Curso> findCursosByProfesorId(Long profesorId) {
        return cursoRepository.findCursosByProfesorId(profesorId);
    }


}
