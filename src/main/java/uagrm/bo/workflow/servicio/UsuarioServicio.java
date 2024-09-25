package uagrm.bo.workflow.servicio;

import uagrm.bo.workflow.entidades.Usuario;

import java.util.List;


public interface UsuarioServicio {

    List<Usuario> obtenerUsuarios();

    Usuario guardarUsuario(Usuario usuario);

    Usuario findByEmail(String email);

    void eliminarUsuario (Long id);

}
