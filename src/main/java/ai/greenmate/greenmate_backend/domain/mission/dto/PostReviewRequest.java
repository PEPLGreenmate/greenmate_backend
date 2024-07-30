package ai.greenmate.greenmate_backend.domain.mission.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReviewRequest {
  private String emotion;
  private String content;

  @Override
  public String toString() {
    return "PostReviewRequest{" +
            "content='" + content + '\'' +
            ", emotion='" + emotion + '\'' +
            '}';
  }
}
