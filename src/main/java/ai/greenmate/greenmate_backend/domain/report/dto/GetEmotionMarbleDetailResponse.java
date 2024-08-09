package ai.greenmate.greenmate_backend.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetEmotionMarbleDetailResponse {
  private Map<String, List<EmotionMarbleDetailDTO>> emotionMarbles;
}
