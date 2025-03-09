package com.example.wordlebackend.servicio;

import com.example.wordlebackend.DTO.AuthenticationResponseDTO;
import com.example.wordlebackend.DTO.RegistroDTO;
import com.example.wordlebackend.DTO.UsuarioDTO;
import com.example.wordlebackend.converter.UsuarioMapper;
import com.example.wordlebackend.modelo.Usuario;
import com.example.wordlebackend.repositorio.UsuarioRepository;
import lombok.AllArgsConstructor;
import com.example.wordlebackend.Security.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}