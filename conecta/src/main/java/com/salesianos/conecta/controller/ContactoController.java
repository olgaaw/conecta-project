package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.CreateContactoDto;
import com.salesianos.conecta.dto.CreateDemandaDto;
import com.salesianos.conecta.dto.GetContactoDto;
import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.service.ContactoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacto/")
@RequiredArgsConstructor
public class ContactoController {

    private final ContactoService contactoService;

    @GetMapping
    public ResponseEntity<List<GetContactoDto>> getAll(){
        return ResponseEntity.ok(contactoService.findAll(false)
                .stream()
                .map(GetContactoDto::of)
                .toList());
    }

    @PostMapping
    public ResponseEntity<GetContactoDto> create(@RequestBody CreateContactoDto dto) {

        return ResponseEntity.status(201).body(contactoService.save(dto));
    }


}
