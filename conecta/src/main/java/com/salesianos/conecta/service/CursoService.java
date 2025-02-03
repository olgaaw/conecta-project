package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.EditCursoCmd;
import com.salesianos.conecta.dto.CreateCursoDto;
import com.salesianos.conecta.error.CursoNotFoundException;
import com.salesianos.conecta.error.TituloNotFoundException;
import com.salesianos.conecta.model.Curso;
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
                .orElseThrow(() -> new TituloNotFoundException());

        return cursoRepository.save(dto.toCurso(titulo));
    }
}
