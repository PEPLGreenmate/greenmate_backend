package ai.greenmate.greenmate_backend.domain.greenmate.repository;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GreenmateRepository extends JpaRepository<Greenmate, Long> {

  @Query("SELECT g FROM Greenmate g JOIN FETCH g.greenmateInfo WHERE g.member = :member")
  List<Greenmate> findByMemberWithGreenmateInfoFetchJoin(Member member);

  Optional<Greenmate> findByMemberAndId(Member member, Long id);
}
