package uagrm.bo.workflow.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.entidades.Cliente;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    public List<Cliente>findAllBy();

}
