package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.CreateEmpresaDto;
import com.salesianos.conecta.dto.CreateUsuarioDto;
import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.Usuario;
import com.salesianos.conecta.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa/")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public List<GetEmpresaDto> getAll(){
        return empresaService.findAll()
                .stream()
                .map(GetEmpresaDto::of)
                .toList();
    }

    @GetMapping("{id}")
    public GetEmpresaDto getById(@PathVariable Long id){
        return GetEmpresaDto.of(empresaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Empresa> create(@RequestBody CreateEmpresaDto dto
    ) {
        return ResponseEntity.status(201).body(empresaService.save(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
