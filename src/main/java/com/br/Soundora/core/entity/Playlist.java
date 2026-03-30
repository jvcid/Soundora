package com.br.Soundora.core.entity;

import com.br.Soundora.core.entity.enums.PlaylistType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Data
@Entity
@Table(name = "playlists")
public class Playlist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(length = 500)
  private String description;

  @Column(length = 300)
  private String coverImage;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PlaylistType type;

  @Column(nullable = false)
  private boolean isPublic = true;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  // Dono da playlist
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // Tracks da playlist (ordem importa, usamos tabela de junção com position)
  @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("position ASC")
  private List<PlaylistTrack> playlistTracks;

  @ManyToMany
  @JoinTable(
      name = "playlist_collaborators",
      joinColumns = @JoinColumn(name = "playlist_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> collaborators;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }
}
