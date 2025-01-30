package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresa/")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<GetEmpresaDto>> getAll(){
        return ResponseEntity.ok(empresaService.findAll()
                .stream()
                .map(GetEmpresaDto::of)
                .toList());
    }

}
