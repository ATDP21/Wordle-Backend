package com.example.wordlebackend.servicios;

import com.example.wordlebackend.DTO.UsuarioNombrePuntuacionDTO;
import com.example.wordlebackend.modelo.Usuario;
import com.example.wordlebackend.repositorio.UsuarioRepository;
import com.example.wordlebackend.servicio.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicePodioTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Test obtenerPodio: retorna 3 usuarios, completando con vacíos si hay menos de 3")
    public void testObtenerPodioConUsuariosInsuficientes() {
        // GIVEN
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario1 = new Usuario();
        usuario1.setNombre("usuario1");
        usuario1.setPuntuacion(1500);
        usuarios.add(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("usuario2");
        usuario2.setPuntuacion(1400);
        usuarios.add(usuario2);

        when(usuarioRepository.findTop3ByOrderByPuntuacionDesc()).thenReturn(usuarios);

        // WHEN
        UsuarioNombrePuntuacionDTO[] podio = usuarioService.obtenerPodio();

        // THEN
        assertEquals(3, podio.length);

        // El primer y segundo elementos contienen los datos reales
        assertEquals("usuario1", podio[0].getNombre());
        assertEquals(1500, podio[0].getPuntuacion());
        assertEquals("usuario2", podio[1].getNombre());
        assertEquals(1400, podio[1].getPuntuacion());

        // El tercer elemento debe ser un "usuario vacío"
        assertEquals("", podio[2].getNombre());
        assertEquals(0, podio[2].getPuntuacion());
    }

    @Test
    @DisplayName("Test obtenerPodio: retorna 3 usuarios cuando hay suficientes")
    public void testObtenerPodioConUsuariosSuficientes() {
        // GIVEN
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario1 = new Usuario();
        usuario1.setNombre("usuario1");
        usuario1.setPuntuacion(1500);
        usuarios.add(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("usuario2");
        usuario2.setPuntuacion(1400);
        usuarios.add(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setNombre("usuario3");
        usuario3.setPuntuacion(1300);
        usuarios.add(usuario3);

        when(usuarioRepository.findTop3ByOrderByPuntuacionDesc()).thenReturn(usuarios);

        // WHEN
        UsuarioNombrePuntuacionDTO[] podio = usuarioService.obtenerPodio();

        // THEN
        assertEquals(3, podio.length);
        assertEquals("usuario1", podio[0].getNombre());
        assertEquals(1500, podio[0].getPuntuacion());

        assertEquals("usuario2", podio[1].getNombre());
        assertEquals(1400, podio[1].getPuntuacion());

        assertEquals("usuario3", podio[2].getNombre());
        assertEquals(1300, podio[2].getPuntuacion());
    }
}
