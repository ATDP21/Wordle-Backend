package com.example.wordlebackend.controlador;


import com.example.wordlebackend.DTO.UsuarioNombrePuntuacionDTO;
import com.example.wordlebackend.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioService perfilService;

  @GetMapping("/loggeado")
  public UsuarioNombrePuntuacionDTO obtenerPerfilLoggeado() {
    return perfilService.obtenerPerfilUsuarioLoggeado();
  }
}

