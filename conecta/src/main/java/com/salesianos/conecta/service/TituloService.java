package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.titulo.CreateTituloDto;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
import com.salesianos.conecta.error.TituloNotFoundException;
import com.salesianos.conecta.model.*;
import com.salesianos.conecta.repository.CursoRepository;
import com.salesianos.conecta.repository.DemandaRepository;
import com.salesianos.conecta.repository.FamiliaProfesionalRepository;
import com.salesianos.conecta.repository.TituloRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TituloService {
    private final TituloRepository tituloRepository;
    private final FamiliaProfesionalRepository familiaProfesionalRepository;
    private final DemandaRepository demandaRepository;
    private final CursoRepository cursoRepository;

    @Transactional
    public List<Titulo> findAll() {
        List<Titulo> result = tituloRepository.findAll();
        if (result.isEmpty()) {
            throw new TituloNotFoundException();
        }

        return result;
    }

    @Transactional
    public Titulo findById(Long id) {
        return tituloRepository.findById(id).orElseThrow(() -> new TituloNotFoundException(id));
    }

    @Transactional
    public Titulo save(CreateTituloDto titulo) {
       Titulo t = new Titulo();
       t.setNombre(titulo.nombre());
       t.setDuracion(titulo.duracion());
       t.setGrado(titulo.grado());
       t.setFamiliaProfesional(familiaProfesionalRepository.findById(titulo.familiaProfesional().getId())
               .orElseThrow(() -> new FamiliaProfesionalNotFoundException(titulo.familiaProfesional().getId())));

       for (Curso c : titulo.curso()) {
           Curso curso = cursoRepository.findById(c.getId())
                   .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
           t.addCurso(curso);
       }

       return tituloRepository.save(t);

    }

    @Transactional
    public Titulo edit(CreateTituloDto titulo, Long id) {
        return tituloRepository.findById(id)
                .map(old -> {
                    old.setNombre(titulo.nombre());
                    old.setDuracion(titulo.duracion());
                    old.setGrado(titulo.grado());
                    old.setFamiliaProfesional(familiaProfesionalRepository.findById(titulo.familiaProfesional().getId())
                            .orElseThrow(() -> new FamiliaProfesionalNotFoundException(titulo.familiaProfesional().getId())));
                    old.getCursos().clear();

                    for (Curso c : titulo.curso()) {
                        old.addCurso(c);
                    }

                    return tituloRepository.save(old);
                })
                .orElseThrow(() -> new TituloNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        Titulo titulo = tituloRepository.findById(id)
                .orElseThrow(() -> new TituloNotFoundException(id));

        for (Curso curso : titulo.getCursos()) {
            demandaRepository.deleteByCursoId(curso.getId());

            for (Profesor profesor : curso.getProfesores()) {
                profesor.getCursos().remove(curso);
            }
            curso.getProfesores().clear();
        }

        tituloRepository.delete(titulo);
    }


}
