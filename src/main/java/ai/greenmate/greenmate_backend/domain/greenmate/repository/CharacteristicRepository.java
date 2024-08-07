package ai.greenmate.greenmate_backend.domain.greenmate.repository;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
