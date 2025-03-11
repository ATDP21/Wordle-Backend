package com.example.wordlebackend.servicio;

import com.example.wordlebackend.DTO.AuthenticationResponseDTO;
import com.example.wordlebackend.DTO.RegistroDTO;
import com.example.wordlebackend.DTO.UsuarioDTO;
import com.example.wordlebackend.DTO.UsuarioNombrePuntuacionDTO;
import com.example.wordlebackend.converter.UsuarioMapper;
import com.example.wordlebackend.modelo.Usuario;
import com.example.wordlebackend.repositorio.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import com.example.wordlebackend.Security.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    private final JWTService jwtService;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findTopByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO){
        return usuarioMapper.toDTO(usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO)));
    }
    public Usuario registrarUsuario(RegistroDTO dto){
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(dto.getUsuario());
        nuevoUsuario.setContraseña(passwordEncoder.encode(dto.getPassword()));
        nuevoUsuario.setEsAdmin(false);

        return usuarioRepository.save(nuevoUsuario);
    }

    public UsuarioDTO getByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findTopByNombre(username).orElse(null);

        if (usuario!=null){
            return usuarioMapper.toDTO(usuario);
        }else{
            throw  new UsernameNotFoundException("Usuario no encontrado");
        }

    }


    public Boolean existByCredentials(String username, String password){
        Usuario usuario = usuarioRepository.findTopByNombre(username).orElse(null);
        return usuario != null  && passwordEncoder.matches(password,usuario.getContraseña());
    }

    public boolean credencialDisponible(String nombreUsuario) {

        Usuario usuario = usuarioRepository.findTopByNombre(nombreUsuario).orElse(null);

        if (usuario == null){
            return true;
        }
        return false;
    }
    @Transactional
    public UsuarioNombrePuntuacionDTO obtenerPerfilUsuarioLoggeado() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT no presente o mal formado");
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractTokenData(token).getUsername();

        System.out.println("🔹 Usuario autenticado: " + username);

        // Buscar usuario por nombre en la base de datos
        Usuario usuario = usuarioRepository.findTopByNombre(username)
                .orElseThrow(() -> new RuntimeException("❌ Usuario no encontrado"));

        System.out.println("✅ Usuario encontrado: " + usuario.getNombre());


        return new UsuarioNombrePuntuacionDTO(
                usuario.getNombre(),
                usuario.getPuntuacion()
        );
    }

    @Transactional
    public void sumarPuntos(Integer puntos) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT no presente o mal formado");
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractTokenData(token).getUsername();

        System.out.println("🔹 Usuario autenticado: " + username);

        // Buscar usuario por nombre en la base de datos
        Usuario usuario = usuarioRepository.findTopByNombre(username)
                .orElseThrow(() -> new RuntimeException("❌ Usuario no encontrado"));

        System.out.println("✅ Usuario encontrado: " + usuario.getNombre());

        usuario.setPuntuacion(usuario.getPuntuacion() + puntos);
        usuarioRepository.save(usuario);
    }
}