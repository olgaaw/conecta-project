package com.salesianos.conecta.controller;


import com.salesianos.conecta.dto.GetCursoDto;
import com.salesianos.conecta.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtiene todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado cursos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCursoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                            "id": 1,
                                                            "nombre": "Primero",
                                                            "horasEmpresa": 400,
                                                            "profesores": [
                                                                {
                                                                    "id": 1,
                                                                    "nombre": "Lucia",
                                                                    "apellidos": "Sanchez Garcia",
                                                                    "email": "lucia@gmail.com",
                                                                    "telefono": 6554321
                                                                },
                                                                {
                                                                    "id": 51,
                                                                    "nombre": "Luis",
                                                                    "apellidos": "Gómez Torres",
                                                                    "email": "lgomez@gmail.com",
                                                                    "telefono": 678548923
                                                                }
                                                            ],
                                                            "nombreTitulo": "Técnico Superior en Desarrollo de Aplicaciones Multiplataforma"
                                                        },
                                                        {
                                                            "id": 51,
                                                            "nombre": "Segundo",
                                                            "horasEmpresa": 300,
                                                            "profesores": [
                                                                {
                                                                    "id": 1,
                                                                    "nombre": "Lucia",
                                                                    "apellidos": "Sanchez Garcia",
                                                                    "email": "lucia@gmail.com",
                                                                    "telefono": 6554321
                                                                }
                                                            ],
                                                            "nombreTitulo": "Técnico en Sistemas Microinformáticos y Redes"
                                                        },
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún curso",
                    content = @Content),
    })
    @GetMapping
    public List<GetCursoDto> getAll() {
        return cursoService.findAll()
                .stream()
                .map(GetCursoDto::of)
                .toList();
    }

}
