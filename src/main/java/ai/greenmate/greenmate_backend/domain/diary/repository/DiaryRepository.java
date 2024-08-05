package ai.greenmate.greenmate_backend.domain.diary.repository;

import ai.greenmate.greenmate_backend.domain.diary.entity.Diary;
import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
  List<Diary> findByGreenmate(Greenmate greenmate);
  List<Diary> findByGreenmateIn(List<Greenmate> greenmates);
}
