package com.br.Soundora.core.dto;

public record FollowerDTO(

        Long id,
        UserDTO follower,
        UserDTO followed

) {}