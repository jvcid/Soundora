package com.br.Soundora.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Entity
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(length = 100)
  private String text;

  @Column private double musicTime;

  @Column(nullable = false)
  private LocalDateTime commentDate;
}
