package uagrm.bo.workflow.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uagrm.bo.workflow.entidades.Usuario;
import uagrm.bo.workflow.repositorio.UsuarioRepositorio;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepo.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario "+ username + " no encontrado"));
        Collection<? extends GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(roles -> new SimpleGrantedAuthority("ROLE_ ".concat(roles.getNombre())))
                .collect(Collectors.toSet());
        return new User(usuario.getNombre(), usuario.getPassword(), true, true,
                true, true, authorities);
    }
}
