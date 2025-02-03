package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.CreateUsuarioDto;
import com.salesianos.conecta.dto.EditUsuarioCmd;
import com.salesianos.conecta.dto.GetUsuarioDto;
import com.salesianos.conecta.dto.ListGetUsuarioDto;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Usuario;
import com.salesianos.conecta.service.ProfesorService;
import com.salesianos.conecta.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ListGetUsuarioDto getAll() {
        return ListGetUsuarioDto.of(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public GetUsuarioDto getById(@PathVariable Long id) {
        return GetUsuarioDto.of(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody CreateUsuarioDto dto
    ) {
        return ResponseEntity.status(201).body(usuarioService.save(dto.toUsuario()));
    }

    @PutMapping("/{id}")
    public Usuario edit(@RequestBody EditUsuarioCmd aEditar, @PathVariable Long id) {
        return usuarioService.edit(aEditar,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();

    }


}
