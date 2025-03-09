package com.example.wordlebackend.converter;


import com.example.wordlebackend.DTO.UsuarioDTO;
import com.example.wordlebackend.modelo.Usuario;
import org.mapstruct.Mapper;


import java.util.List;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
  UsuarioDTO toDTO(Usuario entity);
  Usuario toEntity(UsuarioDTO dto);
  List<UsuarioDTO> toDTO(List<Usuario> listEntity);
  List<Usuario> toEntity(List<UsuarioDTO> listDTOs);
}
