package com.br.Soundora.core.entity.enums;

public enum PlaylistType {
  PERSONAL("Personal"),
  COLLABORATIVE("Collaborative");

  private final String displayName;

  PlaylistType(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
