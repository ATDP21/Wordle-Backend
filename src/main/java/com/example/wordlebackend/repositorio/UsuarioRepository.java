package com.example.wordlebackend.repositorio;

import com.example.wordlebackend.modelo.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM diccionario.usuario WHERE nombre = :nombre", nativeQuery = true)
    Optional<Usuario> findTopByNombre(@Param("nombre") String nombre);

}
