package com.example.wordlebackend.controlador;


import com.example.wordlebackend.DTO.PalabraDTO;
import com.example.wordlebackend.servicio.PalabraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/wordle")
@RestController
public class WordleControlador {

    private final PalabraService palabraService;

    public WordleControlador(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping("/palabra")
    public ResponseEntity<PalabraDTO> obtenerPalabraAleatoria() {
        return ResponseEntity.ok(palabraService.getPalabraAleatoria());
    }
}
