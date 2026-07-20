package com.br.Soundora.core.dto;

public record CreateUserDTO(
        String username,
        String email,
        String password
) {}