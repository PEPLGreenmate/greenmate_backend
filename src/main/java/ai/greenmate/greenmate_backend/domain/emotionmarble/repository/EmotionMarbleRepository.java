package ai.greenmate.greenmate_backend.domain.emotionmarble.repository;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionMarbleRepository extends JpaRepository<EmotionMarble, Long> {
}
