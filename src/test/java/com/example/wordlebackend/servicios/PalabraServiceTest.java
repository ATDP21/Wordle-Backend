package com.example.wordlebackend.servicios;


import com.example.wordlebackend.DTO.PalabraDTO;
import com.example.wordlebackend.modelo.Palabras;
import com.example.wordlebackend.repositorio.PalabraRepository;
import com.example.wordlebackend.servicio.PalabraService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PalabraServiceTest {

    @InjectMocks
    private PalabraService palabraService;

    @Mock
    private PalabraRepository palabraRepository;

    @Test
    @DisplayName("Test de introducción de número de letras de palabra inválido")
    public void TestPalabraNumNeg(){

        //GIVEN
        Integer numLetras = -1;

        //WHEN && THEN
        Exception exception = assertThrows(Exception.class, () -> palabraService.getPalabraAleatoria(numLetras));
        assertEquals("El número de letras es inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Test de introducción de número de letras de palabra válido")
    public void TestPalabraPos(){

        //GIVEN
        Integer numLetras = 5;

        Palabras palabraEsperada = new Palabras(3, "casas", "casas", "casas");

        when(palabraRepository.findPalabraAleatoria(any(Integer.class))).thenReturn(palabraEsperada);

        Palabras palabra = palabraRepository.findPalabraAleatoria(numLetras);
        PalabraDTO palabraDTO = new PalabraDTO(palabra.getId(), palabra.getSinAcentos());

        //WHEN
        palabraService.getPalabraAleatoria(numLetras);

        //THEN
        assertEquals(palabra.getPalabra().length(), palabraDTO.getPalabra().length());
    }



}
