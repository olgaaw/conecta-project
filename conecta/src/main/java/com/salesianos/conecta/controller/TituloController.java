package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.titulo.CreateTituloDto;
import com.salesianos.conecta.dto.titulo.GetTituloDto;
import com.salesianos.conecta.service.TituloService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/titulo/")
@Tag(name = "Titulos", description = "Controlador titulo")
public class TituloController {
    private final TituloService tituloService;

    @Operation(summary = "Obtiene todos los titulos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado titulos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTituloDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                      "nombre": "Técnico Superior en Desarrollo de Aplicaciones Multiplataforma",
                                                      "duracion": 2000,
                                                      "grado": "Grado Superior",
                                                      "familiasProfesionalesDto": {
                                                          "nombre": "Tecnología"
                                                      }
                                                  },
                                                  {
                                                      "nombre": "Técnico en Sistemas Microinformáticos y Redes",
                                                      "duracion": 1500,
                                                      "grado": "Grado Medio",
                                                      "familiasProfesionalesDto": {
                                                          "nombre": "Salud"
                                                      }
                                                  },
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún titulo",
                    content = @Content),
    })
    @GetMapping
    public List<GetTituloDto> getAll() {
        return tituloService.findAll()
                .stream()
                .map(GetTituloDto::of)
                .toList();
    }


    @Operation(summary = "Obtiene un titulo por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Titulo encontrado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTituloDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "nombre": "Técnico Superior en Desarrollo de Aplicaciones Multiplataforma",
                                                    "duracion": 2000,
                                                    "grado": "Grado Superior",
                                                    "familiasProfesionalesDto": {
                                                        "nombre": "Tecnología"
                                                    }
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el titulo ",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetTituloDto getById(@PathVariable Long id) {
        return GetTituloDto.of(tituloService.findById(id));
    }


    @Operation(summary = "Crea un titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Titulo creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateTituloDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el titulo",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetTituloDto> create(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody CreateTituloDto dto) {
        return ResponseEntity.status(201).body(tituloService.save(dto));
    }


    @Operation(summary = "Edita un titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una titulo",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTituloDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                              {
                                                  "nombre": "Técnico en Desarrollo de Aplicaciones Web",
                                                  "duracion": 2000,
                                                  "grado": "Grado Superior",
                                                  "familiasProfesionalesDto": {
                                                      "nombre": "Tecnología"
                                                  }
                                              }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún titulo",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetTituloDto edit(@io.swagger.v3.oas.annotations.parameters.RequestBody CreateTituloDto aEditar, @PathVariable Long id) {
        return tituloService.edit(aEditar, id);
    }


    @Operation(summary = "Elimina un titulo por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Titulo eliminado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tituloService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
