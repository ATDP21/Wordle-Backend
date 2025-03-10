package com.example.wordlebackend.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "palabras_ignacianas", schema = "diccionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PalabrasIgnacianas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "palabra", unique = true, length = 25)
    private String palabra;


}
