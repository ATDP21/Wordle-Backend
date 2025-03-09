package com.example.wordlebackend.DTO;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
  private String usuario;
  private String password;
}
