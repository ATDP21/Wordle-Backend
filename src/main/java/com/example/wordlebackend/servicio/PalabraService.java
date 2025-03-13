package com.example.wordlebackend.servicio;

import com.example.wordlebackend.DTO.PalabraDTO;
import com.example.wordlebackend.modelo.Palabras;
import com.example.wordlebackend.modelo.PalabrasIgnacianas;
import com.example.wordlebackend.repositorio.PalabraIgnacianaRepository;
import com.example.wordlebackend.repositorio.PalabraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PalabraService {
    private final PalabraRepository palabraRepository;
    private final PalabraIgnacianaRepository palabraIgnacianaRepository;

    // Hacer test si y no
    public PalabraDTO getPalabraAleatoria(Integer numLetras) {
        if (numLetras < 4 || numLetras > 8) {
            throw new IllegalArgumentException("El número de letras es inválido");
        }
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

    public Boolean palabraExiste(String palabra) {
        return palabraRepository.existsByPalabra(palabra);
    }
    public void introducirPalabrasNuevas() {
        String filePath = "src/main/resources/static/es.txt";
        List<Palabras> nuevasPalabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() < 9 && line.length() > 4) {
                    // Verificar si la palabra ya está en la base de datos
                    if (!palabraRepository.existsPalabrasBySinAcentos(line)) {
                        System.out.println("Nueva palabra: " + line);
                        Palabras palabra = new Palabras();
                        palabra.setPalabra(line);  // Si necesitas un valor, cámbialo aquí
                        palabra.setSinAcentos(line);
                        palabra.setSensible(line);
                        palabraRepository.save(palabra);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Guardar solo las palabras nuevas en la base de datos
//        if (!nuevasPalabras.isEmpty()) {
//            palabraRepository.saveAll(nuevasPalabras);
//        }
    }
}

