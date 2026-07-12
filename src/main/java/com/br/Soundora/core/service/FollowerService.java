package com.br.Soundora.core.service;

import com.br.Soundora.core.entity.Follower;
import com.br.Soundora.core.exception.EntityNotFoundException;
import com.br.Soundora.core.repository.FollowerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {

  @Autowired public FollowerRepository followerRepository;

  @Autowired public ModelMapper modelMapper;

  public Follower findById(Long id) {
    return followerRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Follower not found"));
  }
}
