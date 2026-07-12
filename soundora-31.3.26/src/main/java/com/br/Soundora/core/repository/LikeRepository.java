package com.br.Soundora.core.repository;

import com.br.Soundora.core.entity.Like;
import com.br.Soundora.core.entity.Track;
import com.br.Soundora.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUsuarioAndTrack(User usuario, Track track);

    int countByTrack(Track track);

}