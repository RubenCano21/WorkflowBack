package uagrm.bo.workflow.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.entidades.ERol;
import uagrm.bo.workflow.entidades.Rol;
import uagrm.bo.workflow.entidades.Usuario;
import uagrm.bo.workflow.repositorio.RolRepositorio;
import uagrm.bo.workflow.repositorio.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Usuario guardarUsuario(@org.jetbrains.annotations.NotNull Usuario usuario) {

        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            Rol clienteRol = rolRepositorio.findByERol(ERol.CLIENTE)
                    .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"));
            usuario.setRoles(List.of(clienteRol));
        }

        List<Rol> roles = new ArrayList<>();


        if (usuario.isAdmin()){
            Optional<Rol> optionalRolAdmin = rolRepositorio.findByERol(ERol.ADMIN);
            optionalRolAdmin.ifPresent(roles::add);

            Optional<Rol> optionalRolRecepcionista = rolRepositorio.findByERol(ERol.RECEPCIONISTA);
            optionalRolRecepcionista.ifPresent(roles::add);

        } else if (usuario.isRecepcionista()) {
            Optional<Rol> optionalRolRecepcionista = rolRepositorio.findByERol(ERol.RECEPCIONISTA);
            optionalRolRecepcionista.ifPresent(roles::add);
        }


        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setNombre(usuario.getNombre());

        return usuarioRepositorio.save(usuario);
    }

    @Override
    @Transactional
    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }

//    // Métodos de conversión
//    private UsuarioDTO convertToDTO(Usuario usuario) {
//        UsuarioDTO dto = new UsuarioDTO();
//        dto.setId(usuario.getId());
//        dto.setNombre(usuario.getNombre());
//        dto.setEmail(usuario.getEmail());
//        dto.setNombre(usuario.getNombre());
//        dto.setRoles(usuario.getRoles().stream()
//                .map(Rol::getERol())
//                .collect(Collectors.toSet()));
//        return dto;
//    }

//    private Usuario convertToEntity(UsuarioDTO dto) {
//        Usuario usuario = new Usuario();
//        usuario.setId(dto.getId());
//        usuario.setNombre(dto.getNombre());
//        usuario.setEmail(dto.getEmail());
//        usuario.setNombre(dto.getNombre());
//        // Los roles pueden ser asignados en otra parte si lo deseas
//        return usuario;
//    }
}