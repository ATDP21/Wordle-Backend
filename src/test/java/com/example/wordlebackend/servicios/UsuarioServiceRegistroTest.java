package com.example.wordlebackend.servicios;

import com.example.wordlebackend.DTO.RegistroDTO;
import com.example.wordlebackend.modelo.Usuario;
import com.example.wordlebackend.repositorio.UsuarioRepository;
import com.example.wordlebackend.servicio.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceRegistroTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("Test de registro de usuario")
    public void testRegistrarUsuario() {
        // GIVEN
        RegistroDTO dto = new RegistroDTO();
        dto.setUsuario("usuarioTest");
        dto.setPassword("passwordTest");

        String encodedPassword = "encodedPasswordTest";
        when(passwordEncoder.encode("passwordTest")).thenReturn(encodedPassword);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            u.setId(1);
            return u;
        });

        // WHEN
        Usuario result = usuarioService.registrarUsuario(dto);

        // THEN
        assertNotNull(result);
        assertEquals("usuarioTest", result.getNombre());
        assertEquals(encodedPassword, result.getContraseña());
        assertFalse(result.getEsAdmin());
        assertEquals(1, result.getId());
    }

}

