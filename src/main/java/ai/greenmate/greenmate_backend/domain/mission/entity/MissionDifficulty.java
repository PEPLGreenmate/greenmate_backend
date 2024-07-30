package ai.greenmate.greenmate_backend.domain.mission.entity;

import lombok.Getter;

@Getter
public enum MissionDifficulty {
  VERY_EASY(5,5,5),
  EASY(10,10,10),
  MEDIUM(15,15,15),
  HARD(20,20,20),
  VERY_HARD(25,25,25),;

  private int waterReward;
  private int bondReward;
  private int experienceReward;

  MissionDifficulty(int bondReward, int experienceReward, int waterReward) {
    this.bondReward = bondReward;
    this.experienceReward = experienceReward;
    this.waterReward = waterReward;
  }
}
