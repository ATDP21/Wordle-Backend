package com.example.wordlebackend.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
  private Integer id;
  private String nombre;
  private String contraseña;
  private boolean esAdmin;

}
