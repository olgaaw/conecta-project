package com.salesianos.conecta.service;

import com.salesianos.conecta.error.UsuarioNotFoundException;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Usuario;
import com.salesianos.conecta.repository.ProfesorRepository;
import com.salesianos.conecta.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository ;
    private final ProfesorRepository profesorRepository;

    public List<Usuario> findAll() {
        List<Usuario> result = usuarioRepository.findAll();
        if (result.isEmpty()) {
            throw new UsuarioNotFoundException();
        }
        return result;
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario save(Usuario usuario) {
        Profesor savedProfesor = profesorRepository.save(usuario.getProfesor());
        usuario.setProfesor(savedProfesor);

        return usuarioRepository.save(Usuario.builder()
                        .username(usuario.getUsername())
                        .password(usuario.getPassword())
                        .role(usuario.getRole())
                        .profesor(usuario.getProfesor())
                .build());
    }

    public Usuario edit(Usuario usuario, Long id) {
        return usuarioRepository.findById(id)
                .map(old -> {
                    old.setUsername(usuario.getUsername());
                    old.setPassword(usuario.getPassword());
                    old.setRole(usuario.getRole());
                    old.setProfesor(usuario.getProfesor());
                    return usuarioRepository.save(usuario);
                }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
