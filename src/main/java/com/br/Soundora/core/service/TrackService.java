package com.br.Soundora.core.service;

import com.br.Soundora.core.entity.Track;
import com.br.Soundora.core.entity.User;
import com.br.Soundora.core.repository.TrackRepository;
import com.br.Soundora.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    public Track uploadTrack(Long userId, Track track) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        track.setUser(user);
        track.setUploadDate(LocalDateTime.now());
        track.setReproductions(0);

        return trackRepository.save(track);
    }

    public List<Track> listarTracks() {
        return trackRepository.findAll();
    }

    public Track buscarPorId(Long id) {
        return trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track não encontrada"));
    }

    public List<Track> buscarPorUsuario(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return trackRepository.findByUser(user);
    }

    public void incrementarReproducoes(Long trackId) {

        Track track = buscarPorId(trackId);

        track.setReproductions(track.getReproductions() + 1);

        trackRepository.save(track);
    }

    public void deletarTrack(Long id) {
        Track track = buscarPorId(id);
        trackRepository.delete(track);
    }
}