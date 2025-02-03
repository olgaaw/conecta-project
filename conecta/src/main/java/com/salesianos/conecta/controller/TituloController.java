package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetCursoDto;
import com.salesianos.conecta.dto.GetTituloDto;
import com.salesianos.conecta.service.TituloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/titulo/")
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
}
