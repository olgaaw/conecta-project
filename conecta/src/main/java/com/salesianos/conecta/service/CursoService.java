package com.salesianos.conecta.service;

import com.salesianos.conecta.error.CursoNotFoundException;
import com.salesianos.conecta.error.UsuarioNotFoundException;
import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.model.Usuario;
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

}
