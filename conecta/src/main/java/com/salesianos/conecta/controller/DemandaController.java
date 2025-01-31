package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.service.DemandaService;
import com.salesianos.conecta.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demanda/")
@RequiredArgsConstructor
public class DemandaController {

    private final DemandaService demandaService;

    @GetMapping
    public List<GetDemandaDto> getAll(){
        return demandaService.findAll()
                .stream()
                .map(GetDemandaDto::of)
                .toList();
    }

    @GetMapping("{id}")
    public GetDemandaDto getById(@PathVariable Long id){
        return GetDemandaDto.of(demandaService.findById(id));
    }

}
