package com.example.wordlebackend.servicio;

import com.example.wordlebackend.DTO.PalabraDTO;
import com.example.wordlebackend.modelo.Palabras;
import com.example.wordlebackend.modelo.PalabrasIgnacianas;
import com.example.wordlebackend.repositorio.PalabraIgnacianaRepository;
import com.example.wordlebackend.repositorio.PalabraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PalabraService {
    private final PalabraRepository palabraRepository;
    private final PalabraIgnacianaRepository palabraIgnacianaRepository;

    public PalabraDTO getPalabraAleatoria(Integer numLetras) {
        Palabras palabra = palabraRepository.findPalabraAleatoria(numLetras);

        PalabraDTO palabraDTO = new PalabraDTO();
        palabraDTO.setId(palabra.getId());
        palabraDTO.setPalabra(palabra.getSinAcentos());

        return palabraDTO;
    }

    public PalabraDTO getPalabraIgncianaAleatoria() {
        PalabrasIgnacianas palabra = palabraIgnacianaRepository.findPalabraIgnacianaAleatoria();
        PalabraDTO palabraDTO = new PalabraDTO();
        palabraDTO.setId(palabra.getId());
        palabraDTO.setPalabra(palabra.getPalabra());
        return palabraDTO;
    }
}
