package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("demandas")
    public List<GetEmpresaDto> getAllDto(){
        return new ArrayList<>(empresaService.findAllDto());
    }

    @GetMapping("{id}")
    public GetEmpresaDto getById(@PathVariable Long id){
        return GetEmpresaDto.of(empresaService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
