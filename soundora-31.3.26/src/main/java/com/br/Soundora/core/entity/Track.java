package com.br.Soundora.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Data
@Entity
@Table(name = "tracks")
public class Track {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 200)
  private String title;

  @Column(length = 500)
  private String description;

  @Column(nullable = false)
  private String urlAudio;

  @Column(nullable = false)
  private String urlCover;

  @Column(nullable = false)
  private LocalDateTime uploadDate;

  @Column(nullable = false)
  private double duration;

  @Column(nullable = false)
  private int reproductions = 0;

  // Privacidade da track
  @Column(nullable = false)
  private boolean isPublic = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToMany
  @JoinTable(
      name = "track_genres",
      joinColumns = @JoinColumn(name = "track_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id"))
  private List<Genre> genres;

  @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Like> likes;

  @PrePersist
  public void prePersist() {
    this.uploadDate = LocalDateTime.now();
  }
}
