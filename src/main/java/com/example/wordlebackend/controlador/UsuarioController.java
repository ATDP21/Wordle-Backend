package com.example.wordlebackend.controlador;


import com.example.wordlebackend.DTO.UsuarioDTO;
import com.example.wordlebackend.DTO.UsuarioNombrePuntuacionDTO;
import com.example.wordlebackend.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioService perfilService;

  @GetMapping("/loggeado")
  public UsuarioNombrePuntuacionDTO obtenerPerfilLoggeado() {
    return perfilService.obtenerPerfilUsuarioLoggeado();
  }
  @PutMapping("/sumarPuntos/{puntos}")
    public void sumarPuntos(@PathVariable Integer puntos){
        perfilService.sumarPuntos(puntos);
    }
    @GetMapping("/podio")
    public UsuarioNombrePuntuacionDTO[] obtenerPodio(){
        return perfilService.obtenerPodio();
    }
}

