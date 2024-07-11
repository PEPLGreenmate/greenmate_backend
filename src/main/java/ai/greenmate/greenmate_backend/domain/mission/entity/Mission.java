package ai.greenmate.greenmate_backend.domain.mission.entity;

import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  @Column(name = "mission_id")
  private long id;
  private String title;
  private String content;
  private int rewardFeed;
  private int rewardInteracton;
  private LocalDateTime deadLine;
  private String reviewContent;
  @Enumerated(value = EnumType.STRING)
  private MissionStatus missionStatus;
  @Enumerated(value = EnumType.STRING)
  private ReviewEmotion reviewEmotion;
}
