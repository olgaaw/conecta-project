package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.contacto.CreateContactoDto;
import com.salesianos.conecta.dto.contacto.GetContactoDto;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import com.salesianos.conecta.service.ContactoService;
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
@RequestMapping("/contacto/")
@RequiredArgsConstructor
@Tag(name = "Contactos", description = "Controlador contacto")
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

    @Operation(summary = "Edita un contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado un contacto",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Contacto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "fecha": "2023-10-01",
                                                "canal": "Email",
                                                "resumen": "Resumen del contacto",
                                                "trabajadorNombre": "David",
                                                "trabajadorEmpresa": "Empresa de Tecnología S.A."
                                            }                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun contacto ",
                    content = @Content),
    })
    @PutMapping("/{profesorId}/{trabajadorId}")
    public ResponseEntity<GetContactoDto> edit(@RequestBody CreateContactoDto aEditar,
                                               @PathVariable Long profesorId,
                                               @PathVariable Long trabajadorId) {
        ContactoPK id = new ContactoPK();
        id.setProfesor_id(profesorId);
        id.setTrabajador_id(trabajadorId);

        GetContactoDto updatedContacto = contactoService.edit(aEditar, id);

        return ResponseEntity.ok(updatedContacto);

    }


}
