package ai.greenmate.greenmate_backend.domain.emotionmarble.entity;

import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmotionMarbleKeyword extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "emotion_marble_keyword_id")
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "emotion_marble_id")
  private EmotionMarble emotionMarble;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "keyword_id")
  private Keyword keyword;

}
