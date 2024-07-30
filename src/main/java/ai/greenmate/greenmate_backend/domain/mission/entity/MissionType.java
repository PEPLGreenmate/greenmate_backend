package ai.greenmate.greenmate_backend.domain.mission.entity;

import lombok.Getter;

@Getter
public enum MissionType {
  WRITING("글쓰기 미션"), TODO("투두 미션");

  private final String type;

  MissionType(String type) {
    this.type = type;
  }
}
