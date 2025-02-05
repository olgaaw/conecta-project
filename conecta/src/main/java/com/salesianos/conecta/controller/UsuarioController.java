package com.salesianos.conecta.controller;

import com.salesianos.conecta.dto.usuario.CreateUsuarioDto;
import com.salesianos.conecta.dto.usuario.EditUsuarioCmd;
import com.salesianos.conecta.dto.usuario.GetUsuarioDto;
import com.salesianos.conecta.dto.usuario.ListGetUsuarioDto;
import com.salesianos.conecta.model.Usuario;
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
import java.util.stream.Collectors;

@Tag(name = "Usuarios", description = "Controlador usuarios")
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Operation(summary = "Obtiene todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado usuarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ListGetUsuarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                       "count": 2,
                                                       "items": [
                                                           {
                                                               "id": 1,
                                                               "username": "aaa",
                                                               "role": "profesor",
                                                               "nombreProfesor": "Lucia",
                                                               "apellidosProfesor": "Sanchez Garcia"
                                                           },
                                                           {
                                                               "id": 51,
                                                               "username": "luisgoto",
                                                               "role": "profesor",
                                                               "nombreProfesor": "Luis",
                                                               "apellidosProfesor": "Gómez Torres"
                                                           }
                                                       ]
                                                   }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún usuario",
                    content = @Content),
    })
    @GetMapping
    public ListGetUsuarioDto getAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ListGetUsuarioDto.of(usuarios);
    }

    @Operation(summary = "Obtiene un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario encontrado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUsuarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                               {
                                                       "id": 1,
                                                       "username": "luciasangar",
                                                       "role": "profesor",
                                                       "nombreProfesor": "Lucia",
                                                       "apellidosProfesor": "Sanchez Garcia"
                                                   }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario ",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetUsuarioDto getById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return GetUsuarioDto.of(usuario);
    }

    @Operation(summary = "Crea un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Usuario creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateUsuarioDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetUsuarioDto> create(@Valid @RequestBody CreateUsuarioDto dto) {
        Usuario usuario = usuarioService.save(dto.toUsuario());
        return ResponseEntity.status(201).body(GetUsuarioDto.of(usuario));
    }

    @Operation(summary = "Edita un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario editado"),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario ",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetUsuarioDto edit(@RequestBody EditUsuarioCmd aEditar, @PathVariable Long id) {
        Usuario usuario = usuarioService.edit(aEditar, id);
        return GetUsuarioDto.of(usuario);
    }

    @Operation(summary = "Elimina un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Usuario eliminado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}