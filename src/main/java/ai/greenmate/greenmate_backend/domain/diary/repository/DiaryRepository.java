package ai.greenmate.greenmate_backend.domain.diary.repository;

import ai.greenmate.greenmate_backend.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
