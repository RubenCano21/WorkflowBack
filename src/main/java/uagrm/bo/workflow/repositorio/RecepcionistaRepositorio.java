package uagrm.bo.workflow.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.entidades.Recepcionista;

import java.util.List;

@Repository
public interface RecepcionistaRepositorio  extends JpaRepository<Recepcionista, Long> {
    public List<Recepcionista> findAllBy();
}
