package ai.greenmate.greenmate_backend.domain.emotionmarble.repository;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarble;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmotionMarbleRepository extends JpaRepository<EmotionMarble, Long> {
  List<EmotionMarble> findByMember(Member member);
  List<EmotionMarble> findByMemberAndCreatedAtBetween(Member member, LocalDateTime startDate, LocalDateTime endDate);
}
