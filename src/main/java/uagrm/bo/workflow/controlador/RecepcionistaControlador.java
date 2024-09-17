package uagrm.bo.workflow.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uagrm.bo.workflow.entidades.Cliente;
import uagrm.bo.workflow.entidades.Recepcionista;
import uagrm.bo.workflow.servicio.ClienteServicioImpl;
import uagrm.bo.workflow.servicio.RecepcionistaServicioImpl;

@Controller
public class RecepcionistaControlador {

    private RecepcionistaServicioImpl servicio;

    @GetMapping("/recepcionista/nuevo")
    public String mostrarFormularioDeRegistrarRecepcionista(Model modelo){
        Recepcionista recepcionista = new Recepcionista();
        modelo.addAttribute("recepcionista", recepcionista);
        return "crear_recepcionista";
    }

    @PostMapping("/recepcionistas")
    public String guardarRecepcionista(@ModelAttribute("recepcionista") Recepcionista recepcionista){
        servicio.guardarRecepcionista(recepcionista);
        return "redirect:/recepcionistas";
    }

    @GetMapping("/recepcionistas/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("recepcionista", servicio.obtenerRecepcionistaPorId(id));
        return "editar_recepcionista";
    }

    @PostMapping("/recepcionistas/{id}")
    public String actualizarRecepcionista(@PathVariable Long id, @ModelAttribute("recepcionista") Recepcionista recepcionista, Model modelo) {
        Recepcionista recepcionistaExistente = servicio.obtenerRecepcionistaPorId(id);
        recepcionistaExistente.setId(id);
        recepcionistaExistente.setNombre(recepcionista.getNombre());
        recepcionistaExistente.setApellido(recepcionista.getApellido());
        recepcionistaExistente.setTelefono(recepcionista.getTelefono());
        recepcionistaExistente.setSexo(recepcionista.getSexo());
        recepcionistaExistente.setDireccion(recepcionista.getDireccion());
        recepcionistaExistente.setCargo(recepcionista.getCargo());


        servicio.actualizarRecepcionista(recepcionistaExistente);
        return "redirect:/recepcionistas";
    }

    @GetMapping("/recepcionistas/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        servicio.eliminarRecepcionista(id);
        return "redirect:/recepcionistas";
    }


}
