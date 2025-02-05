package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.contacto.GetContactoDto;
import com.salesianos.conecta.service.ContactoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacto/")
@RequiredArgsConstructor
@Tag(name = "Contactos", description = "Controlador contacto")
public class ContactoController {

    private final ContactoService contactoService;

    @GetMapping
    public ResponseEntity<List<GetContactoDto>> getAll(){
        return ResponseEntity.ok(contactoService.findAll(false)
                .stream()
                .map(GetContactoDto::of)
                .toList());
    }

}
