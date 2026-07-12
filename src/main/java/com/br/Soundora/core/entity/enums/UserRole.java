package com.br.Soundora.core.entity.enums;

public enum UserRole {
  USER("User"),
  ADMIN("Administrator");

  private final String displayName;

  UserRole(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
