package ai.greenmate.greenmate_backend.domain.mission.dto;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
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
  private long waterReward;
  private long bondReward;
  private LocalDateTime deadLine;
  private String greenmateImageUrl;

  public static MissionDTO fromMissionAndGreenmate(Mission mission, Greenmate greenmate) {
    return MissionDTO.builder()
            .missionId(mission.getId())
            .missionType(mission.getMissionType().getType())
            .title(mission.getTitle())
            .waterReward(mission.getMissionDifficulty().getWaterReward())
            .bondReward(mission.getMissionDifficulty().getBondReward())
            .deadLine(mission.getDeadLine())
            .greenmateImageUrl(greenmate.getGreenmateInfo().getImageUrl())
            .build();
  }
}
