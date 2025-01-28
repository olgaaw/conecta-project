package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.CreateUsuarioDto;
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
    public ResponseEntity<ListGetUsuarioDto> getAll() {
        return ResponseEntity.ok(ListGetUsuarioDto.of(usuarioService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUsuarioDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(GetUsuarioDto.of(usuarioService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody CreateUsuarioDto dto
    ) {
        return ResponseEntity.status(201).body(usuarioService.save(dto.toUsuario()));
    }


}
