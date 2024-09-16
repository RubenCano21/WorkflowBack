package uagrm.bo.workflow.servicio;


import org.springframework.stereotype.Service;
import uagrm.bo.workflow.dto.UsuarioRegistroDTO;
import uagrm.bo.workflow.entidades.Rol;
import uagrm.bo.workflow.entidades.Usuario;
import uagrm.bo.workflow.repositorio.UsuarioRepositorio;

import java.util.Arrays;

@Service
public class UsuarioServicioImpl  implements UsuarioServicio {

    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getEmail(), registroDTO.getPassword(), Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepositorio.save(usuario);
    }

}