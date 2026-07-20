package com.br.Soundora.core.service;

import com.br.Soundora.core.dto.CreateUserDTO;
import com.br.Soundora.core.dto.UserDTO;
import com.br.Soundora.core.entity.User;
import com.br.Soundora.core.entity.enums.UserRole;
import com.br.Soundora.core.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDTO createUser(CreateUserDTO dto) {

    if (userRepository.existsByEmail(dto.email())) {
      throw new RuntimeException("E-mail já cadastrado.");
    }

    if (userRepository.existsByUsername(dto.username())) {
      throw new RuntimeException("Username já está em uso.");
    }

    User user = new User();

    user.setUsername(dto.username());
    user.setEmail(dto.email());
    user.setPassword(passwordEncoder.encode(dto.password()));
    user.setRole(UserRole.USER);

    User savedUser = userRepository.save(user);

    return new UserDTO(
            savedUser.getId(),
            savedUser.getUsername(),
            savedUser.getBio(),
            savedUser.getProfilePicture()
    );
  }
}