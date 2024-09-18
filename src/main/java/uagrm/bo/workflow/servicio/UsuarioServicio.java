package uagrm.bo.workflow.servicio;

import uagrm.bo.workflow.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;


public interface UsuarioServicio {

    List<UsuarioDTO> obtenerUsuarios();

    Optional<UsuarioDTO> obtenerUsuario(String nombre);

    boolean existeUsuario(String nombre);

    boolean existeEmail(String email);

    UsuarioDTO guardarUsuario(UsuarioDTO usuario);

    void eliminarUsuario (Long id);

}
