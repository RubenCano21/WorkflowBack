package uagrm.bo.workflow.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uagrm.bo.workflow.dto.UsuarioRegistroDTO;
import uagrm.bo.workflow.servicio.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    private UsuarioServicio usuarioServicio;

    public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDtTO(){
        return new UsuarioRegistroDTO();
    }

    @GetMapping()
    public String mostrarFormularioDeRegistro(){
        return "registro";
    }

    @PostMapping()
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO){
        usuarioServicio.guardar(registroDTO);
        return "redirect:/registro?exito";
    }

}
