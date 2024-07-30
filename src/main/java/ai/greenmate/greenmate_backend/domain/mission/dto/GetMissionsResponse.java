package ai.greenmate.greenmate_backend.domain.mission.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetMissionsResponse {
  private List<MissionDTO> missions;

  public GetMissionsResponse(List<MissionDTO> missions) {
    this.missions = missions;
  }
}
