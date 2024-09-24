package uagrm.bo.workflow.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.entidades.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
   // boolean existsByEmail(String email);

}
