package ai.greenmate.greenmate_backend.domain.mission.repository;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import ai.greenmate.greenmate_backend.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
  List<Mission> findByGreenmate(Greenmate greenmate);
}
