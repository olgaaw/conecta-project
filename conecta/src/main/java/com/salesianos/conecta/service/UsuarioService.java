package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.profesor.EditProfesorCmd;
import com.salesianos.conecta.dto.usuario.EditUsuarioCmd;
import com.salesianos.conecta.error.ProfesorNotFoundException;
import com.salesianos.conecta.error.UsuarioNotFoundException;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Usuario;
import com.salesianos.conecta.repository.ProfesorRepository;
import com.salesianos.conecta.repository.UsuarioRepository;
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


    public Usuario edit(EditUsuarioCmd usuario, Long id) {
        return usuarioRepository.findById(id)
                .map(old -> {
                    old.setUsername(usuario.username());
                    old.setPassword(usuario.password());
                    return usuarioRepository.save(old);
                }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Profesor editProfesor(EditProfesorCmd profesor, Long id) {
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
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        if (usuario.getProfesor() != null) {
            for (Contacto contacto : usuario.getProfesor().getContactos()) {
                contacto.removeFromProfesor(usuario.getProfesor());
            }
        }

        usuario.setProfesor(null);
        usuarioRepository.delete(usuario);
    }
}
