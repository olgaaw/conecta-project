package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.familiaProfesional.CreateFamiliaprofesionalDto;
import com.salesianos.conecta.dto.familiaProfesional.GetFamiliasProfesionalesDemandasDto;
import com.salesianos.conecta.model.FamiliaProfesional;
import com.salesianos.conecta.service.FamiliaProfesionalService;
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

@RestController
@RequestMapping("/familiaProfesional/")
@RequiredArgsConstructor
@Tag(name = "FamiliaProfesional", description = "El controlador de FamiliaProfesional")
public class FamiliaProfesionalController {

    private final FamiliaProfesionalService familiaProfesionalService;

    @Operation(summary = "Obtiene todas las familias profesionales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado familias profesionales",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FamiliaProfesional.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                    {
                                                        "nombreFamiliaProfesional": "Tecnología",
                                                        "nombreEmpresas": [
                                                            "Empresa de Tecnología S.A."
                                                        ]
                                                    },
                                                    {
                                                        "nombreFamiliaProfesional": "Salud",
                                                        "nombreEmpresas": [
                                                            "Salud y Vida S.L."
                                                        ]
                                                    }
                                                ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna familia profesional",
                    content = @Content),
    })
    @GetMapping
    public List<GetFamiliasProfesionalesDemandasDto> getAll(){
        return familiaProfesionalService.findAll()
                .stream()
                .map(GetFamiliasProfesionalesDemandasDto::of)
                .toList();
    }

    @Operation(summary = "Obtiene una familia profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una familia profesional",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FamiliaProfesional.class)),
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
                    description = "No se ha encontrado ninguna familia profesional",
                    content = @Content),
    })
    @GetMapping("{id}")
    public GetFamiliasProfesionalesDemandasDto getById(@PathVariable Long id){
        return GetFamiliasProfesionalesDemandasDto.of(familiaProfesionalService.findById(id));
    }

    @Operation(summary = "Crea una familia profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una familia profesional",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FamiliaProfesional.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "nombreFamiliaProfesional": "Veterinaria",
                                                 "nombreEmpresas": [
                                                     "Empresa de Tecnología S.A.",
                                                     "Salud y Vida S.L."
                                                 ]
                                             }                                         
                                            """
                            )}
                    )}),
    })
    @PostMapping
    public ResponseEntity<GetFamiliasProfesionalesDemandasDto> create(@io.swagger.v3.oas.annotations.parameters.RequestBody CreateFamiliaprofesionalDto dto
    ) {
        return ResponseEntity.status(201).body(familiaProfesionalService.save(dto));
    }

    @Operation(summary = "Edita una familia profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una familia profesional",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FamiliaProfesional.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombreFamiliaProfesional": "Veterinaria",
                                                "nombreEmpresas": [
                                                    "Empresa de Tecnología S.A.",
                                                    "Salud y Vida S.L."
                                                ]
                                            }                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna familia profesional",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetFamiliasProfesionalesDemandasDto edit(@io.swagger.v3.oas.annotations.parameters.RequestBody CreateFamiliaprofesionalDto aEditar, @PathVariable Long id) {
        return familiaProfesionalService.edit(aEditar, id);
    }

    @Operation(summary = "Elimina una familia profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado una familia profesional",
                    content = @Content),
    })

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        familiaProfesionalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
