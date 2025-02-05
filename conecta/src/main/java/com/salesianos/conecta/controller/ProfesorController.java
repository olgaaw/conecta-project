package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.profesor.EditProfesorCmd;
import com.salesianos.conecta.dto.profesor.GetProfesorDto;
import com.salesianos.conecta.dto.titulo.CreateTituloDto;
import com.salesianos.conecta.dto.titulo.GetTituloDto;
import com.salesianos.conecta.dto.usuario.CreateUsuarioDto;
import com.salesianos.conecta.dto.usuario.EditUsuarioCmd;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Usuario;
import com.salesianos.conecta.service.ProfesorService;
import com.salesianos.conecta.service.UsuarioService;
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
@RequestMapping("/profesor/")
@RequiredArgsConstructor
@Tag(name = "Profesores", description = "Controlador profesor")
public class ProfesorController {
    private final ProfesorService profesorService;
    private final UsuarioService usuarioService;

    @Operation(summary = "Obtiene todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado profesores",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetProfesorDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "nombre": "Lucia",
                                                    "apellidos": "Sanchez Garcia",
                                                    "email": "lucia@gmail.com",
                                                    "telefono": 6554321
                                                },
                                                {
                                                    "id": 51,
                                                    "nombre": "Luis",
                                                    "apellidos": "Gómez Torres",
                                                    "email": "lgomez@gmail.com",
                                                    "telefono": 678548923
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún profesor",
                    content = @Content),
    })
    @GetMapping
    public List<GetProfesorDto> getAll() {
        return profesorService.findAll()
                .stream()
                .map(GetProfesorDto::of)
                .toList();
    }



    @Operation(summary = "Obtiene un profesor por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Profesor encontrado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetProfesorDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                              {
                                                  "id": 1,
                                                  "nombre": "Lucia",
                                                  "apellidos": "Sanchez Garcia",
                                                  "email": "lucia@gmail.com",
                                                  "telefono": 6554321
                                              }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el profesor ",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetProfesorDto getById(@PathVariable Long id) {
        return GetProfesorDto.of(profesorService.findById(id));
    }


    @Operation(summary = "Crea un profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Profesor creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateUsuarioDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el profesor",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody CreateUsuarioDto dto
    ) {
        return ResponseEntity.status(201).body(usuarioService.save(dto.toUsuario()));
    }


    @Operation(summary = "Edita un profesor por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Profesor editado"),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el profesor ",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public Profesor edit(@io.swagger.v3.oas.annotations.parameters.RequestBody EditProfesorCmd aEditar, @PathVariable Long id) {
        return usuarioService.editProfesor(aEditar,id);
    }






}
