package uagrm.bo.workflow.controlador;

//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uagrm.bo.workflow.entidades.Cliente;
import uagrm.bo.workflow.servicio.ClienteServicioImpl;

@Controller
public class ClienteControlador {

    private ClienteServicioImpl servicio;

    /*
    @GetMapping("/clientes")
    public String listarClientes(Model modelo, @Param("palabraClave") String palabraClave){
        modelo.addAttribute("clientes", servicio.listarTodosLosClientes("palabraClave"));
        modelo.addAttribute("palabraClave", palabraClave);
        return "clientes";
    }*/

    @GetMapping("/clientes/nuevo")
    public String mostrarFormularioDeRegistrarCliente(Model modelo){
        Cliente cliente = new Cliente();
        modelo.addAttribute("cliente", cliente);
        return "crear_cliente";
    }

    @PostMapping("/clientes")
    public String guardarCliente(@ModelAttribute("clientes") Cliente cliente){
        servicio.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("cliente", servicio.obtenerClientePorId(id));
        return "editar_cliente";
    }

    @PostMapping("/clientes/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute("cliente") Cliente cliente, Model modelo) {
        Cliente clienteExistente = servicio.obtenerClientePorId(id);
        clienteExistente.setId(id);
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setSexo(cliente.getSexo());


        servicio.actualizarCliente(clienteExistente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        servicio.eliminarCliente(id);
        return "redirect:/clientes";
    }

    /*
    @GetMapping("/exportarPDF")
    public void exportarListadoDeEmpleadosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Clientes_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Cliente> clientes = servicio.findAll();

        ExportarPdf exporter = new ExportarPdf(clientes);
        exporter.exportar(response);
    }
     */


}
