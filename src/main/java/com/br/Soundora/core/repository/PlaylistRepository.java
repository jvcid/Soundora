package com.br.Soundora.core.repository;

import com.br.Soundora.core.entity.Playlist;
import com.br.Soundora.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    List<Playlist> findByUsuario(User usuario);

}