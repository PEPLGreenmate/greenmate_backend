package ai.greenmate.greenmate_backend.domain.report.dto;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarble;
import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.Keyword;
import ai.greenmate.greenmate_backend.global.util.DateUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class EmotionMarbleDetailDTO {
  private String title;
  private String date;
  private List<String> keywords;

  @Builder
  public EmotionMarbleDetailDTO(String title, String date, List<String> keywords) {
    this.title = title;
    this.date = date;
    this.keywords = keywords;
  }

  public static EmotionMarbleDetailDTO of(EmotionMarble emotionMarble, List<Keyword> keywords) {
    return EmotionMarbleDetailDTO.builder()
            .title(emotionMarble.getTitle())
            .date(DateUtils.getDateFromLocalDateTime(emotionMarble.getCreatedAt(), "yyyy-MM-dd"))
            .keywords(keywords.stream()
                    .map(Keyword::getKeyword)
                    .toList())
            .build();
  }

  @Override
  public String toString() {
    return "EmotionMarbleDetailDTO{" +
            "title='" + title + '\'' +
            ", date='" + date + '\'' +
            ", keywords=" + keywords +
            '}';
  }
}
