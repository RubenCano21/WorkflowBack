package uagrm.bo.workflow.servicio;

import uagrm.bo.workflow.dto.UsuarioRegistroDTO;
import uagrm.bo.workflow.entidades.Usuario;

public interface UsuarioServicio {

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

}
