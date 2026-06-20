package com.example.ms_user.Controller;

import com.example.ms_user.Model.User;
import com.example.ms_user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los Usuarios")
public class UserController {

    @Autowired
    private UserService service;

  
    @PostMapping
     @Operation(summary = "Guardar", description = "Guarda los usuarios")
    public ResponseEntity<User> crear(@RequestBody User usuario) {
        User nuevoUsuario = service.save(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "Obtener Usuarios",
        description = "Obtiene todos los usuarios"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "usuarios encontrados exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = User.class))),
    @ApiResponse(responseCode = "404", description = "No hay Usuarios encontrados")
})
    public List<User> Listar() {
        return service.getUsers();
    }
    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Obtener Usuarios por Nombre",
        description = "Obtiene los usuarios por el nombre registrado"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = User.class))),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
})
    public ResponseEntity<List<User>> getByNombre(@PathVariable("nombre") String nombre) {
        List<User> usuarios = service.getUsersByNombre(nombre);
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener los usuarios por ID", description = "obtiene los usuarios por su ID")
    public ResponseEntity<Map<String, Object>> obtenerDetalleCompleto(@PathVariable Integer id) {
       
        Map<String, Object> respuesta = service.buscarUsuarioCompleto(id);

        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar usuarios por ID", description = "borra los usuarios por su ID")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        service.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
    @PutMapping("/{id}")
    @Operation(summary = "modifica usuarios por ID", description = "modifica los usuarios por su ID")
    public ResponseEntity<User> actualizar(@PathVariable Integer id, @RequestBody User usuario) {
     try {
        User user = service.findById(id);
        user.setId(id);
        user.setNombre(usuario.getNombre());
        

        service.save(user);
        return ResponseEntity.ok(usuario);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}
}
