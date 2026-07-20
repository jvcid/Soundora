package com.br.Soundora.core.dto.auth;

public record LoginRequestDTO(
        String email,
        String password
) {}