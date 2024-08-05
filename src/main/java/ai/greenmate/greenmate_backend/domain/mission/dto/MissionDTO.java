package ai.greenmate.greenmate_backend.domain.mission.dto;

import ai.greenmate.greenmate_backend.domain.mission.entity.Mission;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MissionDTO {
  private long missionId;
  private String title;
  private String missionType;
  private String greenmateType;
  private long waterReward;
  private long bondReward;
  private LocalDateTime deadLine;

  public static MissionDTO fromMissionAndGreenmate(Mission mission) {
    return MissionDTO.builder()
            .missionId(mission.getId())
            .missionType(mission.getMissionTypeToLowerCase())
            .title(mission.getTitle())
            .waterReward(mission.getMissionDifficulty().getWaterReward())
            .bondReward(mission.getMissionDifficulty().getBondReward())
            .deadLine(mission.getDeadLine())
            .greenmateType(mission.getGreenmate().getGreenmateTypeToLowerCase())
            .build();
  }
}
