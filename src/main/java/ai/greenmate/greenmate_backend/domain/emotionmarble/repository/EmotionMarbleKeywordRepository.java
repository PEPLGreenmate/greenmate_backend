package ai.greenmate.greenmate_backend.domain.emotionmarble.repository;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarbleKeyword;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmotionMarbleKeywordRepository extends JpaRepository<EmotionMarbleKeyword, Long> {

  @Query("SELECT emk " +
          "FROM EmotionMarbleKeyword emk " +
          "JOIN FETCH emk.emotionMarble em " +
          "JOIN FETCH emk.keyword k " +
          "WHERE em.member = :member " +
          "AND em.createdAt BETWEEN :startDate AND :endDate")
  List<EmotionMarbleKeyword> findByMonthAndEmotionMarblesInFetchJoin(Member member, LocalDateTime startDate, LocalDateTime endDate);

}
