package uagrm.bo.workflow.servicio;

import uagrm.bo.workflow.dto.UsuarioDTO;
import uagrm.bo.workflow.entidades.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioServicio {

    List<Usuario> obtenerUsuarios();

    boolean existeUsuario(String nombre);

    Usuario guardarUsuario(Usuario usuario);

    void eliminarUsuario (Long id);

}
