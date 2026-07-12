package com.br.Soundora.core.repository;

import com.br.Soundora.core.entity.Comment;
import com.br.Soundora.core.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTrack(Track track);

}