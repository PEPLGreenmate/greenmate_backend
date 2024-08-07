package ai.greenmate.greenmate_backend.domain.greenmate.repository;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.GreenmateCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenmateCharacteristicRepository extends JpaRepository<GreenmateCharacteristic, Long> {
}
