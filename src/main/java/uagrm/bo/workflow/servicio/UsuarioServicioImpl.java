package uagrm.bo.workflow.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uagrm.bo.workflow.dto.UsuarioDTO;
import uagrm.bo.workflow.entidades.Rol;
import uagrm.bo.workflow.entidades.Usuario;
import uagrm.bo.workflow.repositorio.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServicioImpl  implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioRepositorio.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> obtenerUsuario(String nombre) {
        return usuarioRepositorio.findByNombre(nombre)
                .map(this::convertToDTO);
    }

    @Override
    public boolean existeUsuario(String nombre) {
        return usuarioRepositorio.existsByNombre(nombre);
    }

    @Override
    public boolean existeEmail(String email) {
        return usuarioRepositorio.existsByEmail(email);
    }

    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioRegistroDTO) {
        Usuario usuario = convertToEntity(usuarioRegistroDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario guardarUsuario = usuarioRepositorio.save(usuario);

        return convertToDTO(guardarUsuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    // Métodos de conversión
    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setNombre(usuario.getNombre());
        dto.setRoles(usuario.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet()));
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setNombre(dto.getNombre());
        // Los roles pueden ser asignados en otra parte si lo deseas
        return usuario;
    }
}