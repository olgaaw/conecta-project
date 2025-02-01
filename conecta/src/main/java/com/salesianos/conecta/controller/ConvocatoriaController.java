package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetConvocatoriaDto;
import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.service.ConvocatoriaService;
import com.salesianos.conecta.service.DemandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convocatoria/")
@RequiredArgsConstructor
public class ConvocatoriaController {

    private final ConvocatoriaService convocatoriaService;

    @GetMapping
    public List<GetConvocatoriaDto> getAll(){
        return convocatoriaService.findAll()
                .stream()
                .map(GetConvocatoriaDto::of)
                .toList();
    }

    @GetMapping("{id}")
    public GetConvocatoriaDto getById(@PathVariable Long id){
        return GetConvocatoriaDto.of(convocatoriaService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        convocatoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}