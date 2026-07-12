package com.br.Soundora.core.dto;

public record TrackDTO(

        Long id,
        String title,
        String description,
        String urlAudio,
        String urlCover,
        double duration,
        int reproductions,
        UserDTO user


) {}
