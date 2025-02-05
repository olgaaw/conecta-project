package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.contacto.CreateContactoDto;
import com.salesianos.conecta.dto.contacto.GetContactoDto;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
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

    @Operation(summary = "Obtiene un contacto")
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
                    description = "No se ha encontrado ningun contacto",
                    content = @Content),
    })
    @GetMapping(("/{profesorId}/{trabajadorId}"))
    public GetContactoDto getById(@PathVariable Long profesorId,
                                 @PathVariable Long trabajadorId) {
        ContactoPK id = new ContactoPK();
        id.setProfesor_id(profesorId);
        id.setTrabajador_id(trabajadorId);

        return GetContactoDto.of(contactoService.findById(id));
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

        Contacto contacto = contactoService.save(dto);
        return ResponseEntity.status(201).body(GetContactoDto.of(contacto));
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
    public GetContactoDto edit(@RequestBody CreateContactoDto aEditar, @PathVariable Long profesorId, @PathVariable Long trabajadorId) {
        ContactoPK id = new ContactoPK();
        id.setProfesor_id(profesorId);
        id.setTrabajador_id(trabajadorId);

        Contacto contacto = contactoService.edit(aEditar, id);

        return GetContactoDto.of(contacto);

    }

    @DeleteMapping("/{profesorId}/{trabajadorId}")
    public ResponseEntity<?> delete(@PathVariable Long profesorId, @PathVariable Long trabajadorId) {
        ContactoPK id = new ContactoPK();
        id.setProfesor_id(profesorId);
        id.setTrabajador_id(trabajadorId);

        contactoService.delete(id);

        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Obtiene todos los contactos de una familia profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado contactos",
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
                    description = "No se ha encontrado ningun contacto con esa familia profesional",
                    content = @Content),
    })
    @GetMapping("/familiaProfesional/{nombreFamilia}")
    public List<GetContactoDto> getContactosByFamilia(@PathVariable String nombreFamilia) {
        List<Contacto> contactos = contactoService.findByFamiliaProfesional(nombreFamilia);

        if (contactos.isEmpty()){
            throw new FamiliaProfesionalNotFoundException(nombreFamilia);
        }

        return contactos.stream().map(GetContactoDto::of).toList();
    }

    @Operation(summary = "Obtiene todos los contactos de una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado contactos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Contacto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "fecha": "2025-01-15",
                                                    "canal": "llamada",
                                                    "resumen": "Cerrada fecha inicio de practicas primero daw",
                                                    "trabajadorNombre": "Olga",
                                                    "trabajadorEmpresa": "Salud y Vida S.L."
                                                }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun contacto con esa empresa",
                    content = @Content),
    })
    @GetMapping("/empresa/{empresaId}")
    public List<GetContactoDto> getContactosByEmpresaId(@PathVariable Long empresaId) {
        List<Contacto> contactos = contactoService.findByEmpresa(empresaId);

        if (contactos.isEmpty()) {
            throw new EmpresaNotFoundException(empresaId);
        }

        return contactos.stream().map(GetContactoDto::of).toList();
    }


    @GetMapping("/profesor/{profesorId}")
    public List<GetContactoDto> getContactosByProfesorId(@PathVariable Long profesorId) {
        return contactoService.findContactosByProfesorId(profesorId)
                .stream()
                .map(GetContactoDto::of)
                .toList();
    }


}
