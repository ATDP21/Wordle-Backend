package com.example.wordlebackend.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioNombrePuntuacionDTO {
    private String nombre;
    private Integer puntuacion;

}
