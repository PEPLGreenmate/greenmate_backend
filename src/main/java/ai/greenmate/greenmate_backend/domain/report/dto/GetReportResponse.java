package ai.greenmate.greenmate_backend.domain.report.dto;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionType;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetReportResponse {
  private long angerCount;
  private long sadnessCount;
  private long anxietyCount;
  private long hurtCount;
  private long embarrassCount;
  private long joyCount;

  private int depressScore;
  private int stressScore;
  private int anxietyScore;
  private String depressTestAt;
  private String stressTestAt;
  private String anxietyTestAt;

  public static GetReportResponse from(Map<EmotionType, Long> emotionCountMap, Member member) {
    return GetReportResponse.builder()
            .angerCount(emotionCountMap.getOrDefault(EmotionType.ANGER,0L))
            .sadnessCount(emotionCountMap.getOrDefault(EmotionType.SADNESS, 0L))
            .anxietyCount(emotionCountMap.getOrDefault(EmotionType.ANXIETY, 0L))
            .hurtCount(emotionCountMap.getOrDefault(EmotionType.HURT, 0L))
            .embarrassCount(emotionCountMap.getOrDefault(EmotionType.EMBARRASSMENT, 0L))
            .joyCount(emotionCountMap.getOrDefault(EmotionType.JOY, 0L))
            .depressScore(member.getDepressScore())
            .stressScore(member.getStressScore())
            .anxietyScore(member.getStressScore())
            .depressTestAt(DateUtils.getDateFromLocalDateTime(member.getDepressTestAt()))
            .stressTestAt(DateUtils.getDateFromLocalDateTime(member.getStressTestAt()))
            .anxietyTestAt(DateUtils.getDateFromLocalDateTime(member.getAnxietyTestAt()))
            .build();
  }
}
