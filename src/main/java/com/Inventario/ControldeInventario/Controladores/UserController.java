package com.Inventario.ControldeInventario.Controladores;

import com.Inventario.ControldeInventario.Entidades.User;
import com.Inventario.ControldeInventario.Servicios.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserService usuarioService;

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return usuarioService.login(request.getNombre(), request.getPassword());
    }
    
    //Esto es un edpoint el cual cacha los datos
    @GetMapping("") 
    public List<User> getTodosLosUsuarios(){
        return usuarioService.obtenerTodosLosUsuarios();
    }
    
    //
    @PostMapping("")
    public List<User> postInsertarUsuario(@RequestBody User a){ //recuerda que el nombre de la variable siempre debe ser igual
        if (usuarioService.insertarUsuario(a)){
            return usuarioService.obtenerTodosLosUsuarios();
        }else{
            return null;
        }
    }
    
    @PutMapping("")
    //Recibimos valores por body
    public List<User> putActualizarUsuario(@RequestBody User a){ //recuerda que el nombre de la variable siempre debe ser igual
        if (usuarioService.actualizarUsuario(a)){
            return usuarioService.obtenerTodosLosUsuarios();
        }else{
            return null;
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable Long id) {
    try {
        // Lógica para obtener el usuario por ID utilizando el servicio
        Optional<User> usuarioOptional = usuarioService.obtenerUsuarioPorId(id);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            // Devuelve un 404 Not Found si el usuario no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } catch (Exception e) {
        // Manejo de excepciones y devolución de un 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarios(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuarios(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     
}
