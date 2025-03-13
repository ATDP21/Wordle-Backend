package com.example.wordlebackend.servicios;

import com.example.wordlebackend.DTO.PalabraDTO;
import com.example.wordlebackend.DTO.RegistroDTO;
import com.example.wordlebackend.modelo.Palabras;
import com.example.wordlebackend.modelo.Usuario;
import com.example.wordlebackend.repositorio.PalabraRepository;
import com.example.wordlebackend.repositorio.UsuarioRepository;
import com.example.wordlebackend.servicio.PalabraService;
import com.example.wordlebackend.servicio.UsuarioService;
import com.example.wordlebackend.Security.JWTService;
import com.example.wordlebackend.Security.TokenDataDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PalabraRepository palabraRepository;

    @Mock
    private PalabraService palabraService;

    @Mock
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Mock
    private JWTService jwtService;

    @BeforeEach
    public void setUp() throws NoSuchMethodException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer testtoken");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Simulamos la extracción de datos del token con un TokenDataDTO real
        // TokenDataDTO(username, rol, fecha_creacion, fecha_expiracion)
        when(jwtService.extractTokenData("testtoken")).thenReturn(
                (TokenDataDTO) JWTService.class.getDeclaredMethod("extractTokenData", String.class)
                        .getReturnType()
                        .cast(
                                TokenDataDTO.builder()
                                        .username("testUser")
                                        .rol("false")
                                        .fecha_creacion(System.currentTimeMillis())
                                        .fecha_expiracion(System.currentTimeMillis() + 1000 * 60 * 60 * 3)
                                        .build()
        ));
    }

    @Test
    @DisplayName("Test de introducción de puntos inválidos")
    public void testSumarPuntosNegativos() {
        // GIVEN
        Integer puntos = -390;

        Usuario usuario = new Usuario();
        usuario.setNombre("testUser");
        usuario.setPuntuacion(1000);
        when(usuarioRepository.findTopByNombre("testUser"))
                .thenReturn(Optional.of(usuario));

        // WHEN & THEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> usuarioService.sumarPuntos(puntos));
        assertEquals("El número de puntos no puede ser negativo", exception.getMessage());
    }

    @Test
    @DisplayName("Test de introducción de puntos válidos")
    public void testSumarPuntosValidos() {
        // GIVEN
        Integer puntos = 390;
        Usuario usuario = new Usuario();
        usuario.setNombre("testUser");
        usuario.setPuntuacion(1000);

        when(usuarioRepository.findTopByNombre("testUser"))
                .thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // WHEN
        usuarioService.sumarPuntos(puntos);

        // THEN
        assertEquals(1390, usuario.getPuntuacion());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("Test de registro de usuario")
    public void testRegistrarUsuario() {
        // GIVEN
        RegistroDTO dto = new RegistroDTO();
        dto.setUsuario("usuarioTest");
        dto.setPassword("passwordTest");

        String encodedPassword = "encodedPasswordTest";
        when(passwordEncoder.encode("passwordTest")).thenReturn(encodedPassword);

        // Configuramos el repositorio para que al guardar retorne el usuario que se le pase
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            // Simulamos que el repositorio asigna un ID al usuario, por ejemplo 1L
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
        verify(passwordEncoder, times(1)).encode("passwordTest");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}

