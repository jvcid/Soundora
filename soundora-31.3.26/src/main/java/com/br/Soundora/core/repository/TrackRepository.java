package com.br.Soundora.core.repository;

import com.br.Soundora.core.entity.Track;
import com.br.Soundora.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findByUsuario(User usuario);

    List<Track> findByTituloContainingIgnoreCase(String titulo);
}