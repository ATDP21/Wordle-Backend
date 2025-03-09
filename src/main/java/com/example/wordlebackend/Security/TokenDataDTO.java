package com.example.wordlebackend.Security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDataDTO {

  //Usaremos este DTO para la generación de Tokens

  private String username;
  private String rol;
  private Long fecha_creacion;
  private Long fecha_expiracion;

}
