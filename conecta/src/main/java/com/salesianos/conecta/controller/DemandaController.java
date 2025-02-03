package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.service.DemandaService;
import com.salesianos.conecta.service.EmpresaService;
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
@RequestMapping("/demanda/")
@RequiredArgsConstructor
@Tag(name = "Demanda", description = "El controlador de Demanda")
public class DemandaController {

    private final DemandaService demandaService;

    @Operation(summary = "Obtiene todas las demandas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado demandas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Demanda.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "nombreEmpresa": "Empresa de Tecnología S.A.",
                                                      "nombreTitulo": "Técnico Superior en Desarrollo de Aplicaciones Multiplataforma",
                                                      "Curso": "Primero",
                                                      "cantidadAlumnos": 3
                                                  },
                                                  {
                                                      "nombreEmpresa": "Salud y Vida S.L.",
                                                      "nombreTitulo": "Técnico Superior en Desarrollo de Aplicaciones Multiplataforma",
                                                      "Curso": "Primero",
                                                      "cantidadAlumnos": 1
                                                  },                                             
                                              ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna demanda",
                    content = @Content),
    })
    @GetMapping
    public List<GetDemandaDto> getAll(){
        return demandaService.findAll()
                .stream()
                .map(GetDemandaDto::of)
                .toList();
    }

    @Operation(summary = "Obtiene una demanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una demanda",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Demanda.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombreEmpresa": "Empresa de Tecnología S.A.",
                                                "nombreTitulo": "Técnico Superior en Desarrollo de Aplicaciones Multiplataforma",
                                                "Curso": "Primero",
                                                "cantidadAlumnos": 3
                                            }                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna demanda",
                    content = @Content),
    })
    @GetMapping("{id}")
    public GetDemandaDto getById(@PathVariable Long id){
        return GetDemandaDto.of(demandaService.findById(id));
    }

    @Operation(summary = "Elimina una demanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado una demanda",
                    content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        demandaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
