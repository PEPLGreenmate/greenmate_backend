package ai.greenmate.greenmate_backend.domain.emotionmarble.entity;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmotionMarble extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;

  @Enumerated(value = EnumType.STRING)
  private EmotionType emotionType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "emotionMarble", fetch = FetchType.LAZY)
  private List<EmotionMarbleKeyword> emotionMarbleKeywords;

  public String getEmotionToLowerCase() {
    return this.emotionType.name().toLowerCase();
  }

  @Override
  public String toString() {
    return "EmotionMarble{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", emotionType=" + emotionType +
            ", member=" + member +
            '}';
  }
}
