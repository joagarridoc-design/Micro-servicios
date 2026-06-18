package com.example.ms_user.Controller;

import com.example.ms_user.Model.User;
import com.example.ms_user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los Usuarios")
public class UserController {

    @Autowired
    private UserService service;

    // MÉTODO PARA ESCRIBIR EN LA BD
    @PostMapping
     @Operation(summary = "Guardar", description = "Guarda los usuarios")
    public ResponseEntity<User> crear(@RequestBody User usuario) {
        // guarda el usuario, JPA lo inserta en 'usuario',
        // 'perfil' (por el Cascade) y en 'usuario_juego_ids'.
        User nuevoUsuario = service.save(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
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
        user.setPerfil(usuario.getPerfil());

        service.save(user);
        return ResponseEntity.ok(usuario);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}
}
