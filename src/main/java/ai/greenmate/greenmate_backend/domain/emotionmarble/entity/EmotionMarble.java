package ai.greenmate.greenmate_backend.domain.emotionmarble.entity;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.mission.entity.Mission;
import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmotionMarble extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;
  private String emotion; // TODO : Enum Class에 담을지?

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToOne
  @JoinColumn(name = "mission_id")
  private Mission mission;
}
