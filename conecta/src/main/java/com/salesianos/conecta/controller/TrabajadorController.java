package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.trabajador.CreateTrabajadorDto;
import com.salesianos.conecta.dto.trabajador.GetTrabajadorDto;
import com.salesianos.conecta.model.Trabajador;
import com.salesianos.conecta.service.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Trabajadores", description = "Controlador trabajadores")
@RestController
@RequiredArgsConstructor
@RequestMapping("/trabajador/")
public class TrabajadorController {
    private final TrabajadorService trabajadorService;

    @Operation(summary = "Obtiene todos los trabajadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado trabajadores",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTrabajadorDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                             {
                                                    "nombre": "David",
                                                    "apellidos": "Sevillano Dominguez",
                                                    "email": "dvd.sevi@gmail.com",
                                                    "telefono": 65479023,
                                                    "area": "Desarrollo",
                                                    "puesto": "Mobile",
                                                    "empresaDto": {
                                                        "nombre": "Empresa de Tecnología S.A.",
                                                        "direccion": "Calle Falsa 123",
                                                        "familiaProfesionales": [
                                                            {
                                                                "nombre": "Tecnología"
                                                            }
                                                        ],
                                                        "demandas": 1
                                                    }
                                                },
                                                {
                                                    "nombre": "Olga",
                                                    "apellidos": "Valor Wu",
                                                    "email": "ovaolowu@gmail.com",
                                                    "telefono": 666789547,
                                                    "area": "Diseño",
                                                    "puesto": "ux/ui designer",
                                                    "empresaDto": {
                                                        "nombre": "Salud y Vida S.L.",
                                                        "direccion": "Avenida Siempre Viva 742",
                                                        "familiaProfesionales": [
                                                            {
                                                                "nombre": "Salud"
                                                            }
                                                        ],
                                                        "demandas": 1
                                                    }
                                                }
                                            
                                              ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún trabajador",
                    content = @Content),
    })
    @GetMapping
    public List<GetTrabajadorDto> getAll(){
        return trabajadorService.findAll()
                .stream()
                .map(GetTrabajadorDto::of)
                .toList();
    }


    @Operation(summary = "Obtiene un trabajador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado un trabajador",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTrabajadorDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Salud y Vida S.L.",
                                                "direccion": "Avenida Siempre Viva 742",
                                                "familiaProfesionales": [
                                                    {
                                                        "nombre": "Salud"
                                                    }
                                                ],
                                                "demandas": 1
                                            }                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún trabajador",
                    content = @Content),
    })
    @GetMapping("{id}")
    public GetTrabajadorDto getById(@PathVariable Long id){
        return GetTrabajadorDto.of(trabajadorService.findById(id));
    }


    @Operation(summary = "Crea un trabajador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Traajador creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateTrabajadorDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el trabajador",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetTrabajadorDto> create(@RequestBody CreateTrabajadorDto dto
    ) {
        Trabajador trabajador = trabajadorService.save(dto);
        return ResponseEntity.status(201).body(GetTrabajadorDto.of(trabajador));
    }


    @Operation(summary = "Edita un trabajador por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Trabajador editado"),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el trabajador ",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetTrabajadorDto edit(@RequestBody CreateTrabajadorDto aEditar, @PathVariable Long id) {
        Trabajador trabajador = trabajadorService.edit(aEditar, id);
        return GetTrabajadorDto.of(trabajador);
    }


    @Operation(summary = "Elimina un curso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Curso eliminado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        trabajadorService.delete(id);
        return ResponseEntity.noContent().build();

    }


    @Operation(summary = "Obtiene todos los trabajadores de un área por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado trabajadores",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTrabajadorDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                               {
                                                   "nombre": "David",
                                                   "apellidos": "Sevillano Dominguez",
                                                   "email": "dvd.sevi@gmail.com",
                                                   "telefono": 65479023,
                                                   "area": "Desarrollo",
                                                   "puesto": "Mobile",
                                                   "empresaDto": {
                                                       "nombre": "Empresa de Tecnología S.A.",
                                                       "direccion": "Calle Falsa 123",
                                                       "familiaProfesionales": [
                                                           {
                                                               "nombre": "Tecnología"
                                                           }
                                                       ],
                                                       "demandas": 1
                                                   }
                                               }
                                           ]                      
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404", description = "No se encontraron trabajadores para el área especificada")
    })
    @GetMapping("/area/{area}")
    public List<GetTrabajadorDto> getTrabajadoresByArea(@PathVariable String area) {
        return trabajadorService.findTrabajadoresByArea(area)
                .stream()
                .map(GetTrabajadorDto::of)
                .toList();
    }




}
