package ai.greenmate.greenmate_backend.domain.mission.entity;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarble;
import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private String content;
  private LocalDateTime deadLine;
  private String reviewContent;

  @Enumerated(EnumType.STRING)
  @Column(name = "difficulty")
  private MissionDifficulty missionDifficulty;
  @Enumerated(value = EnumType.STRING)
  private MissionStatus missionStatus;
  @Enumerated(value = EnumType.STRING)
  private ReviewEmotion reviewEmotion;
  @Enumerated(value = EnumType.STRING)
  private MissionType missionType;

  @OneToOne
  @JoinColumn(name = "emotion_marble_id")
  private EmotionMarble emotionMarble;

  @ManyToOne
  @JoinColumn(name = "greenmate_id")
  private Greenmate greenmate;

  public void updateMissionStatus(MissionStatus missionStatus) {
    this.missionStatus = missionStatus;
  }

  public void updateReview(String emotion, String content) {
    if (emotion != null) {
      this.reviewEmotion = ReviewEmotion.valueOf(emotion);
    }
    if(content != null) {
      this.reviewContent = content;
    }
  }

  public String getMissionTypeToLowerCase() {
    return this.missionType.name().toLowerCase();
  }
}
