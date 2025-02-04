package com.salesianos.conecta.controller;


import com.salesianos.conecta.dto.EditCursoCmd;
import com.salesianos.conecta.dto.CreateCursoDto;
import com.salesianos.conecta.dto.GetCursoDto;
import com.salesianos.conecta.model.Curso;
import com.salesianos.conecta.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


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



    @Operation(summary = "Obtiene un curso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Curso encontrado",
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
                                               }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el curso ",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetCursoDto getById(@PathVariable Long id) {
        return GetCursoDto.of(cursoService.findById(id));
    }


    @Operation(summary = "Crea un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Curso creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateCursoDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el curso",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetCursoDto> create(@Valid @RequestBody CreateCursoDto dto) {
        Curso curso = cursoService.save(dto);
        return ResponseEntity.status(201).body(GetCursoDto.of(curso));
    }


    @Operation(summary = "Edita un curso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Curso editado"),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el curso ",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public Curso edit(@RequestBody EditCursoCmd curso, @PathVariable Long id) {
        return cursoService.edit(curso, id);
    }


    @Operation(summary = "Elimina un curso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Curso eliminado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
