package com.example.wordlebackend.controlador;

import lombok.AllArgsConstructor;
import com.example.wordlebackend.DTO.AuthenticationResponseDTO;
import com.example.wordlebackend.DTO.LoginDTO;
import com.example.wordlebackend.DTO.RegistroDTO;
import com.example.wordlebackend.Security.AuthenticationService;
import com.example.wordlebackend.modelo.Usuario;
import com.example.wordlebackend.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

  private UsuarioService usuarioService;

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/registro")
  public Usuario registro(@RequestBody RegistroDTO registroDTO){

    return usuarioService.registrarUsuario(registroDTO);
  }

  @PostMapping("/login")
  public AuthenticationResponseDTO register(@RequestBody LoginDTO loginDTO){
    if(authenticationService.verifyPassword(loginDTO)){
      return authenticationService.login(loginDTO);
    }else{
      return AuthenticationResponseDTO.builder().message("Invalid credentials").build();
    }
  }

  @GetMapping("/credencialDisponible")
  public boolean credencialDisponible(@RequestParam String usuario){
    return usuarioService.credencialDisponible(usuario);
  }

}
