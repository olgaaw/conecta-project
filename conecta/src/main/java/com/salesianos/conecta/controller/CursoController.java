package com.salesianos.conecta.controller;


import com.salesianos.conecta.dto.GetCursoDto;
import com.salesianos.conecta.service.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Cursos", description = "Controlador cursos")
@RestController
@RequiredArgsConstructor
@RequestMapping("/curso/")
public class CursoController {
    private final CursoService cursoService;

    @GetMapping
    public List<GetCursoDto> getAll() {
        return cursoService.findAll()
                .stream()
                .map(GetCursoDto::of)
                .toList();
    }

}
