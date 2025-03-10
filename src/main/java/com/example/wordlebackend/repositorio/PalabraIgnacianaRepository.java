package com.example.wordlebackend.repositorio;

import com.example.wordlebackend.modelo.Palabras;
import com.example.wordlebackend.modelo.PalabrasIgnacianas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PalabraIgnacianaRepository extends JpaRepository<Palabras, String> {

    @Query(value= "SELECT p from Palabras p where p.id = 1")
    Palabras findPalabrasById();


    @Query(value = "SELECT * FROM diccionario.palabras_ignacianas " +
            "WHERE id >= (SELECT floor(random() * (SELECT max(id)::double precision FROM diccionario.palabras_ignacianas))) " +
            "ORDER BY id LIMIT 1",
            nativeQuery = true)
    PalabrasIgnacianas findPalabraIgnacianaAleatoria();

}
