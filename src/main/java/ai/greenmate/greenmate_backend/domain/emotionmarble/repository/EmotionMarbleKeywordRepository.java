package ai.greenmate.greenmate_backend.domain.emotionmarble.repository;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarbleKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionMarbleKeywordRepository extends JpaRepository<EmotionMarbleKeyword, Long> {
}
