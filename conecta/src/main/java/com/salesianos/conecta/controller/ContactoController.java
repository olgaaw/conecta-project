package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetContactoDto;
import com.salesianos.conecta.service.ContactoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacto/")
@RequiredArgsConstructor
public class ContactoController {

    private final ContactoService contactoService;

    @GetMapping
    public List<GetContactoDto> getAll(){
        return contactoService.findAll()
                .stream()
                .map(GetContactoDto::of)
                .toList();
    }

}
