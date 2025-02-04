package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.CreateEmpresaDto;
import com.salesianos.conecta.dto.CreateUsuarioDto;
import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.dto.GetEmpresaStringsDto;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.Usuario;
import com.salesianos.conecta.service.EmpresaService;
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
@RequestMapping("/empresa/")
@RequiredArgsConstructor
@Tag(name = "Empresa", description = "El controlador de Empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Operation(summary = "Obtiene todas las empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado empresas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Empresa.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                   {
                                                       "nombre": "Empresa de Tecnología S.A.",
                                                       "direccion": "Calle Falsa 123",
                                                       "familiaProfesionales": [
                                                           {
                                                               "nombre": "Tecnología"
                                                           }
                                                       ],
                                                       "demandas": 1
                                                   },
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
                                               ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna empresa",
                    content = @Content),
    })
    @GetMapping
    public List<GetEmpresaDto> getAll(){
        return empresaService.findAll()
                .stream()
                .map(GetEmpresaDto::of)
                .toList();
    }

    @Operation(summary = "Obtiene una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una empresa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Empresa.class)),
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
                    description = "No se ha encontrado ninguna empresa",
                    content = @Content),
    })
    @GetMapping("{id}")
    public GetEmpresaDto getById(@PathVariable Long id){
        return GetEmpresaDto.of(empresaService.findById(id));
    }

    @Operation(summary = "Crea una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una empresa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Empresa.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Nombre de la Empresa",
                                                "direccion": "Dirección de la Empresa",
                                                "familiasProfesionales": [
                                                    "Tecnología",
                                                    "Salud"
                                                ],
                                                "demandas": 2
                                            }                                         
                                            """
                            )}
                    )}),
    })
    @PostMapping
    public ResponseEntity<GetEmpresaStringsDto> create(@RequestBody CreateEmpresaDto dto
    ) {
        return ResponseEntity.status(201).body(empresaService.save(dto));
    }

    @Operation(summary = "Edita una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una empresa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Empresa.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "nomnbre": "souhisvdoiuhj de la Empresa",
                                                 "direccion": "svnuipdjsodilnjv de la Empresa",
                                                 "familiasProfesionales": [
                                                     "Salud",
                                                     "Tecnología"
                                                 ],
                                                 "demandas": 4
                                             }                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna empresa",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetEmpresaStringsDto edit(@RequestBody CreateEmpresaDto aEditar, @PathVariable Long id) {
        return empresaService.edit(aEditar, id);
    }

    @Operation(summary = "Elimina una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado una empresa",
                    content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
