package uagrm.bo.workflow.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uagrm.bo.workflow.entidades.Recepcionista;
import uagrm.bo.workflow.repositorio.RecepcionistaRepositorio;

import java.util.List;

@Service
public class RecepcionistaServicioImpl {

    @Autowired
    private RecepcionistaRepositorio repositorio;

    //@Transactional(readOnly = true)
    public List<Recepcionista> findAll(){
        return (List<Recepcionista>) repositorio.findAll();
    }

    public List<Recepcionista> listarTodosLosRecepcionista(){
        return repositorio.findAll();
    }

    public void guardarRecepcionista(Recepcionista recepcionista){
        repositorio.save(recepcionista);
    }

    public Recepcionista obtenerRecepcionistaPorId(Long id){
        return repositorio.findById(id).get();
    }

    public Recepcionista actualizarRecepcionista(Recepcionista recepcionista){
        return repositorio.save(recepcionista);
    }

    public void eliminarRecepcionista(Long id){
        repositorio.deleteById(id);
    }
}
