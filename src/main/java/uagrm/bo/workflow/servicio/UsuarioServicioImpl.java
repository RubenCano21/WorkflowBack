package uagrm.bo.workflow.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.UsuarioDTO;
import uagrm.bo.workflow.entidades.Rol;
import uagrm.bo.workflow.entidades.Usuario;
import uagrm.bo.workflow.repositorio.RolRepositorio;
import uagrm.bo.workflow.repositorio.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServicioImpl  implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepositorio.findAll();
    }


    @Override
    @Transactional
    public boolean existeUsuario(String nombre) {
        return usuarioRepositorio.existsByNombre(nombre);
    }

    @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {

        Optional<Rol> optionalRol = rolRepositorio.findByNombre("USER");
        List<Rol> roles = new ArrayList<>();

        optionalRol.ifPresent(roles::add);

        if (usuario.isAdmin()){
            Optional<Rol> optionalRolAdmin = rolRepositorio.findByNombre("ADMIN");
            optionalRolAdmin.ifPresent(roles::add);
        }

        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepositorio.save(usuario);
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