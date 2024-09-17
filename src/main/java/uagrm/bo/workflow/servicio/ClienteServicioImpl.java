package uagrm.bo.workflow.servicio;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uagrm.bo.workflow.entidades.Cliente;
import uagrm.bo.workflow.repositorio.ClienteRepositorio;

import java.util.List;

@Service
public class ClienteServicioImpl {

    @Autowired
    private ClienteRepositorio repositorio;

    //@Transactional(readOnly = true)
    public List<Cliente>findAll(){
        return (List<Cliente>) repositorio.findAll();
    }

    /*public List<Cliente> listarTodosLosClientes(String palabraClave){
        if(palabraClave != null){
            return repositorio.findAll(palabraClave);
        }
        return repositorio.findAll();
    }*/

    public List<Cliente> listarTodosLosClientes(){
        return repositorio.findAll();
    }

    public Cliente guardarCliente(Cliente cliente){
        return repositorio.save(cliente);
    }

    public Cliente obtenerClientePorId(Long id){
        return repositorio.findById(id).get();
    }

    public Cliente actualizarCliente(Cliente cliente){
        return repositorio.save(cliente);
    }

    public void eliminarCliente(Long id){
        repositorio.deleteById(id);
    }

}
