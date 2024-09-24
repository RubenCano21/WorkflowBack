package uagrm.bo.workflow.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.UsuarioDTO;
import uagrm.bo.workflow.entidades.Usuario;
import uagrm.bo.workflow.servicio.UsuarioServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;


    @ModelAttribute("/usuario")
    public UsuarioDTO retornarNuevoUsuarioRegistroDtTO(){
        return new UsuarioDTO();
    }

//    @GetMapping()
//    public String mostrarFormularioDeRegistro(){
//        return "registro";
//    }

   @GetMapping
    public List<Usuario> mostrarListaDeUsuarios(){
        return usuarioServicio.obtenerUsuarios();
   }


   @PostMapping
   public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario,
                                                  BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServicio.guardarUsuario(usuario));
   }


    @PostMapping("/registrar")
   public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario,
                                             BindingResult result){
        usuario.setAdmin(false);

        return crearUsuario(usuario, result);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
   }


   //valida que se ingresen campos correctos
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() +" " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);
    }

}
