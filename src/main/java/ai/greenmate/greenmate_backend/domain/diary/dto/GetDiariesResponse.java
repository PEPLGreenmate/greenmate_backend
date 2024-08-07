package ai.greenmate.greenmate_backend.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetDiariesResponse {
  private List<DiaryDTO> diaries;
}
