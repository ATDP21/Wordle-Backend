package com.example.wordlebackend.repositorio;

import com.example.wordlebackend.modelo.Palabras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PalabraRepository extends JpaRepository<Palabras, String> {

    @Query(value= "SELECT p from Palabras p where p.id = 1")
    Palabras findPalabrasById();


    @Query(value = "SELECT * FROM diccionario.palabras p " +
            "WHERE LENGTH(p.palabra) = :numLetras " +
            "AND id >= (SELECT floor(random() * (SELECT max(id)::double precision FROM diccionario.palabras))) " +
            "ORDER BY id LIMIT 1",
            nativeQuery = true)
    Palabras findPalabraAleatoria(@Param("numLetras") int numLetras);



}
