package com.example.wordlebackend.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "usuario", schema = "diccionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  @EqualsAndHashCode.Include
  private String nombre;

  @Column(nullable = false, length = 225)
  private String contraseña;

  @Column(nullable = false, columnDefinition = "integer default 0")
  private int puntuacion;

  @Column(nullable = false, columnDefinition = "boolean default false")
  private boolean esAdmin;



}



