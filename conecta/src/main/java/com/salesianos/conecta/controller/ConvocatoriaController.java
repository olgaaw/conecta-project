package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetConvocatoriaDto;
import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.service.ConvocatoriaService;
import com.salesianos.conecta.service.DemandaService;
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
@RequestMapping("/convocatoria/")
@RequiredArgsConstructor
@Tag(name = "Convocatoria", description = "El controlador de Convocatoria")
public class ConvocatoriaController {

    private final ConvocatoriaService convocatoriaService;

    @Operation(summary = "Obtiene todas las convocatorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado convocatorias",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Convocatoria.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "cursoEscolar": "2024-2025",
                                                     "nombre": "Septiembre",
                                                     "numeroDemandas": 3
                                                 },
                                                 {
                                                     "cursoEscolar": "2024-2025",
                                                     "nombre": "Diciembre",
                                                     "numeroDemandas": 1
                                                 }
                                             ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna convocatoria",
                    content = @Content),
    })
    @GetMapping
    public List<GetConvocatoriaDto> getAll(){
        return convocatoriaService.findAll()
                .stream()
                .map(GetConvocatoriaDto::of)
                .toList();
    }

    @Operation(summary = "Obtiene una convocatoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una convocatoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Convocatoria.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "cursoEscolar": "2024-2025",
                                                     "nombre": "Septiembre",
                                                     "numeroDemandas": 3
                                                 }
                                             ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna convocatoria",
                    content = @Content),
    })
    @GetMapping("{id}")
    public GetConvocatoriaDto getById(@PathVariable Long id){
        return GetConvocatoriaDto.of(convocatoriaService.findById(id));
    }

    @Operation(summary = "Elimina una convocatoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado una convocatoria",
                     content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        convocatoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}