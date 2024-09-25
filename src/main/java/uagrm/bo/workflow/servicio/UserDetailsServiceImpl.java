package uagrm.bo.workflow.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.entidades.Usuario;
import uagrm.bo.workflow.repositorio.UsuarioRepositorio;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepo.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Usuario %s no encontrado", username)));

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getERol().name()))
                .collect(Collectors.toList());

        return new User(
                usuario.getNombre(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
