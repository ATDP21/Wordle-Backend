package com.example.wordlebackend.controlador;


import com.example.wordlebackend.DTO.PalabraDTO;
import com.example.wordlebackend.servicio.PalabraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/wordle")
@RestController
public class WordleControlador {

    private final PalabraService palabraService;

    public WordleControlador(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping("/palabra/{numLetras}")
    public ResponseEntity<PalabraDTO> obtenerPalabraAleatoria(@PathVariable Integer numLetras) {
        return ResponseEntity.ok(palabraService.getPalabraAleatoria(numLetras));
    }
    @GetMapping("/palabraIgnaciana")
    public ResponseEntity<PalabraDTO> obtenerPalabraIgnacianaAleatoria() {
        return ResponseEntity.ok(palabraService.getPalabraIgncianaAleatoria());
    }
}
