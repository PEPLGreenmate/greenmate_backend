package ai.greenmate.greenmate_backend.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDTO {
  private long diaryId;
  private LocalDate createdDate;
  private String content;
}
