package com.br.Soundora.core.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@Entity
@Table(name = "genres")
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String genreName;

  @Column(length = 200)
  private String description;

  @ManyToMany(mappedBy = "genres")
  private List<Track> tracks;
}
