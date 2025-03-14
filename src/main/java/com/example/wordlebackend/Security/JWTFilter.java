package com.example.wordlebackend.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.example.wordlebackend.modelo.Usuario;
import com.example.wordlebackend.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

      final String authHeader = request.getHeader("Authorization");
      final String jwt;
      final String username;



       //Si viene por la url "auth" lo dejamos pasar
      if (request.getServletPath().contains("/auth")){
        filterChain.doFilter(request, response);
        return;
      }

      if(authHeader==null || !authHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
      }

      jwt = authHeader.substring(7);
      username = jwtService.extractUsername(jwt);

      if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(username); //Aquí hemos hecho un cast pero parece innecesario

          if(jwtService.isTokenValid(jwt, usuario)){
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              usuario.getUsername(),
              usuario.getPassword(),
              usuario.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
          }
      }

        filterChain.doFilter(request, response);
    }
}
