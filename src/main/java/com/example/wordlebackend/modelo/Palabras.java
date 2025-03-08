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
    private String id;

    @Column(name = "palabra")
    private String palabra;

    @Column(name = "sin_acentos")
    private String sinAcentos;

    @Column(name = "sensible")
    private String sensible;


}
