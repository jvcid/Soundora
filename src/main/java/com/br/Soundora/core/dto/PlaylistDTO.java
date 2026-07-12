package com.br.Soundora.core.dto;

public record PlaylistDTO(

        Long id,
        String name,
        String description,
        String coverImage,
        UserDTO user

) {}
