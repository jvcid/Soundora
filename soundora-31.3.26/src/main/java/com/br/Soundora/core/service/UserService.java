package com.br.Soundora.core.service;

import com.br.Soundora.core.entity.User;
import com.br.Soundora.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User criarUsuario(User user) {

    if (user.getEmail() == null || user.getEmail().isBlank()) {
      throw new RuntimeException("Email obrigatório");
    }

    if (userRepository.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Email já em uso");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    return userRepository.save(user);
  }

  public User buscarPorId(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
  }

  public List<User> listarUsuarios() {
    return userRepository.findAll();
  }

  public User buscarPorEmail(String email) {
    return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
  }

  public User atualizarUsuario(Long id, User userAtualizado) {

    User user = buscarPorId(id);

    if (userAtualizado.getUsername() != null) {
      user.setUsername(userAtualizado.getUsername());
    }

    if (userAtualizado.getBio() != null) {
      user.setBio(userAtualizado.getBio());
    }

    if (userAtualizado.getProfilePicture() != null) {
      user.setProfilePicture(userAtualizado.getProfilePicture());
    }

    return userRepository.save(user);
  }

  public void deletarUsuario(Long id) {
    User user = buscarPorId(id);
    userRepository.delete(user);
  }
}