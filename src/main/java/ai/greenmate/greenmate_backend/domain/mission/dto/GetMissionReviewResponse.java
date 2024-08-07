package ai.greenmate.greenmate_backend.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMissionReviewResponse {
  private String emotion;
  private String content;

  @Builder
  private GetMissionReviewResponse(String content, String emotion) {
    this.content = content;
    this.emotion = emotion;
  }
}
