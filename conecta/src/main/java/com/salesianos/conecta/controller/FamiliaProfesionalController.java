package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.dto.GetFamiliasProfesionalesDemandasDto;
import com.salesianos.conecta.service.DemandaService;
import com.salesianos.conecta.service.FamiliaProfesionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/familiaProfesional/")
@RequiredArgsConstructor
public class FamiliaProfesionalController {

    private final FamiliaProfesionalService familiaProfesionalService;

    @GetMapping
    public List<GetFamiliasProfesionalesDemandasDto> getAll(){
        return familiaProfesionalService.findAll()
                .stream()
                .map(GetFamiliasProfesionalesDemandasDto::of)
                .toList();
    }
/*
    @GetMapping("{id}")
    public GetFamiliasProfesionalesDemandasDto getById(@PathVariable Long id){
        return GetFamiliasProfesionalesDemandasDto.of(familiaProfesionalService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        familiaProfesionalService.delete(id);
        return ResponseEntity.noContent().build();
    }
*/
}
