package com.br.Soundora.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 * Entidade de junção entre Playlist e Track. Usamos uma entidade própria (em vez de @ManyToMany
 * simples) para armazenar a posição da track na playlist e quem a adicionou.
 */
@Data
@Entity
@Table(
    name = "playlist_tracks",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"playlist_id", "track_id"})})
public class PlaylistTrack {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "playlist_id", nullable = false)
  private Playlist playlist;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "track_id", nullable = false)
  private Track track;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "added_by_user_id", nullable = false)
  private User addedBy;

  @Column(nullable = false)
  private int position;

  @Column(nullable = false)
  private LocalDateTime addedAt;

  @PrePersist
  public void prePersist() {
    this.addedAt = LocalDateTime.now();
  }
}
