package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.CreateContactoDto;
import com.salesianos.conecta.dto.CreateDemandaDto;
import com.salesianos.conecta.dto.GetContactoDto;
import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.service.ContactoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacto/")
@RequiredArgsConstructor
public class ContactoController {

    private final ContactoService contactoService;

    @Operation(summary = "Obtiene todos los contactos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado contacots",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Contacto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                         "fecha": "2025-01-17",
                                                         "canal": "email",
                                                         "resumen": "Aceptación del convenio de practicas",
                                                         "trabajadorNombre": "David",
                                                         "trabajadorEmpresa": "Empresa de Tecnología S.A."
                                                     }
                                             ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun contacto",
                    content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<GetContactoDto>> getAll(){
        return ResponseEntity.ok(contactoService.findAll(false)
                .stream()
                .map(GetContactoDto::of)
                .toList());
    }

    @Operation(summary = "Crea un contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un contacto",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Contacto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "fecha": "2023-10-01",
                                                 "canal": "Email",
                                                 "resumen": "Resumen del contacto",
                                                 "trabajadorNombre": "Olga",
                                                 "trabajadorEmpresa": "Salud y Vida S.L."
                                             }                                         
                                            """
                            )}
                    )}),
    })
    @PostMapping
    public ResponseEntity<GetContactoDto> create(@RequestBody CreateContactoDto dto) {

        return ResponseEntity.status(201).body(contactoService.save(dto));
    }

    @Operation(summary = "Edita una demanda")
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
                                                   "cantidadAlumnos": 5
                                               }                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna demanda",
                    content = @Content),
    })
    @PutMapping("/{profesorId}/{trabajadorId}")
    public ResponseEntity<GetContactoDto> edit(@RequestBody CreateContactoDto aEditar,
                                               @PathVariable Long profesorId,
                                               @PathVariable Long trabajadorId) {
        // Crea el ContactoPK utilizando los IDs de profesor y trabajador
        ContactoPK id = new ContactoPK();
        id.setProfesor_id(profesorId);
        id.setTrabajador_id(trabajadorId);

        // Llama al servicio para editar el contacto
        GetContactoDto updatedContacto = contactoService.edit(aEditar, id);

        // Devuelve la respuesta con el contacto actualizado
        return ResponseEntity.ok(updatedContacto);

    }

}
