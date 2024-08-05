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
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMissionDetailResponse {
  private long missionId;
  private String title;
  private String content;
  private String missionType;
  private long waterReward;
  private long bondReward;
  private LocalDateTime deadLine;
  private String missionStatus;

  public static GetMissionDetailResponse from(Mission mission) {
    return GetMissionDetailResponse.builder()
            .missionId(mission.getId())
            .title(mission.getTitle())
            .content(mission.getContent())
            .missionType(mission.getMissionTypeToLowerCase())
            .waterReward(mission.getMissionDifficulty().getWaterReward())
            .bondReward(mission.getMissionDifficulty().getBondReward())
            .deadLine(mission.getDeadLine())
            .missionStatus(mission.getMissionStatus().getAlias())
            .build();

  }
}
