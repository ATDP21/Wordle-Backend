package com.example.wordlebackend.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "palabras", schema = "diccionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Palabras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "palabra", unique = true, length = 25)
    private String palabra;

    @Column(name = "sin_acentos", unique = true, length = 25)
    private String sinAcentos;

    @Column(name = "sensible", unique = true, length = 25)
    private String sensible;


}
