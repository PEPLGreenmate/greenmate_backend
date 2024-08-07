package ai.greenmate.greenmate_backend.domain.greenmate.repository;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.GreenmateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenmateInfoRepository extends JpaRepository<GreenmateInfo, Long> {
}
