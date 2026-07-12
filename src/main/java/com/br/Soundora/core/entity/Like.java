package com.br.Soundora.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Entity
@Table(
    name = "likes",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "track_id"})})
public class Like {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Track track;

  @Column(nullable = false)
  private LocalDateTime data;
}
