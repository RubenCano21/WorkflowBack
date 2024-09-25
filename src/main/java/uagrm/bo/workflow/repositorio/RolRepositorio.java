package uagrm.bo.workflow.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.entidades.ERol;
import uagrm.bo.workflow.entidades.Rol;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {

    @Query("SELECT r FROM Rol r WHERE r.eRol = :eRol")
    Optional<Rol> findByERol(ERol eRol);
}
