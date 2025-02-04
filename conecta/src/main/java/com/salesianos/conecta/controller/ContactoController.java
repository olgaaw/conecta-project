package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.CreateContactoDto;
import com.salesianos.conecta.dto.CreateDemandaDto;
import com.salesianos.conecta.dto.GetContactoDto;
import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.Convocatoria;
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


}
