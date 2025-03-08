package com.example.wordlebackend.servicio;

import com.example.wordlebackend.DTO.PalabraDTO;
import com.example.wordlebackend.modelo.Palabras;
import com.example.wordlebackend.repositorio.PalabraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PalabraService {
    private final PalabraRepository palabraRepository;

    public PalabraDTO getPalabraAleatoria() {
        Palabras palabra = palabraRepository.findPalabraAleatoria();

        PalabraDTO palabraDTO = new PalabraDTO();
        palabraDTO.setId(palabra.getId());
        palabraDTO.setPalabra(palabra.getSinAcentos());

        return palabraDTO;
    }
}
