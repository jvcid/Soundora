package com.br.Soundora.core.repository;

import com.br.Soundora.core.entity.Follower;
import com.br.Soundora.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    boolean existsBySeguidorAndSeguido(User seguidor, User seguido);

    int countBySeguido(User user);

}