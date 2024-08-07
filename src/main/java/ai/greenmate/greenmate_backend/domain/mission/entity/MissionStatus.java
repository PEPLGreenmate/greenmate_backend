package ai.greenmate.greenmate_backend.domain.mission.entity;

import lombok.Getter;

@Getter
public enum MissionStatus {
  NOT_ACCEPTED("available"), IN_PROGRESS("ongoing"), FAILED("failed"), COMPLETED("completed");

  private final String alias;

  MissionStatus(String alias) {
    this.alias = alias;
  }

  public static MissionStatus from(String status) {
    for (MissionStatus missionStatus : MissionStatus.values()) {
      if (missionStatus.alias.equals(status)) {
        return missionStatus;
      }
    }
    return null;
  }
}
