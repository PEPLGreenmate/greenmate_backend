package ai.greenmate.greenmate_backend.domain.member.repository;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByEmail(String email);
  Optional<Member> findByRefreshToken(String refreshToken);
  Optional<Member> findByNickname(String nickname);
}
