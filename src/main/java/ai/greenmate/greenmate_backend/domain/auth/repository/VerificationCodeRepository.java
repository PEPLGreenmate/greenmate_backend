package ai.greenmate.greenmate_backend.domain.auth.repository;

import ai.greenmate.greenmate_backend.domain.auth.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
  List<VerificationCode> findByEmail(String email);
}
