package uagrm.bo.workflow.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.UsuarioDTO;
import uagrm.bo.workflow.servicio.UsuarioServicio;

import java.util.List;

@RestController
@RequestMapping("/api/usarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;


    @ModelAttribute("usuario")
    public UsuarioDTO retornarNuevoUsuarioRegistroDtTO(){
        return new UsuarioDTO();
    }

    @GetMapping()
    public String mostrarFormularioDeRegistro(){
        return "registro";
    }

   @GetMapping
    public List<UsuarioDTO> mostrarListaDeUsuarios(){
        return usuarioServicio.obtenerUsuarios();
   }

   @GetMapping("/nombre")
   public ResponseEntity<UsuarioDTO> mostrarUsuarioByNombre(@PathVariable String nombre){
        return usuarioServicio.obtenerUsuario(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        if (usuarioServicio.existeUsuario(usuarioDTO.getNombre()) ||
                usuarioServicio.existeEmail(usuarioDTO.getEmail())){
            return ResponseEntity.badRequest().build();
        }
        UsuarioDTO nueUsuarioDTO = usuarioServicio.guardarUsuario(usuarioDTO);
        return ResponseEntity.ok(nueUsuarioDTO);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
   }


}
