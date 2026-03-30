package com.br.Soundora.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Entity
@Table(
    name = "follows",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"usuario_seguidor_id", "usuario_seguido_id"})
    })
public class Follower {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "usuario_seguidor_id", nullable = false)
  private User follower;

  @ManyToOne
  @JoinColumn(name = "usuario_seguido_id", nullable = false)
  private User followed;

  @Column(nullable = false)
  private LocalDateTime data;
}
