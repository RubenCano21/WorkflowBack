package uagrm.bo.workflow.security.filtros;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uagrm.bo.workflow.security.jwt.JWTUtils;
import uagrm.bo.workflow.servicio.UserDetailsServiceImpl;

import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

       try {
           String tokenHeader = request.getHeader("Authorization");

           if (tokenHeader == null || tokenHeader.startsWith("Bearer ")) {
               filterChain.doFilter(request, response);
               return;
           }

           String token = tokenHeader.substring(7);

           if (!jwtUtils.isValidToken(token)){
               throw new IllegalArgumentException("Token invalido o expirado");
           }

           String nombreUsuario = jwtUtils.getNombreUsuarioFromToken(token);
           UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);

           if (userDetails != null){
               UsernamePasswordAuthenticationToken authenticationToken =
                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       } catch (IllegalArgumentException | UsernameNotFoundException e) {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.getWriter().write("Error de autenticacion" + e.getMessage());
           return;
       }
       filterChain.doFilter(request, response);
    }
}
