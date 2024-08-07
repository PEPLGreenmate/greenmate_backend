package ai.greenmate.greenmate_backend.domain.greenmate.repository;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.GreenmateInfo;
import ai.greenmate.greenmate_backend.domain.greenmate.entity.GreenmateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreenmateInfoRepository extends JpaRepository<GreenmateInfo, Long> {
  Optional<GreenmateInfo> findByGreenmateType(GreenmateType greenmateType);
}
