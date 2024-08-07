package ai.greenmate.greenmate_backend.domain.diary.service;

import ai.greenmate.greenmate_backend.domain.diary.dto.DiaryDTO;
import ai.greenmate.greenmate_backend.domain.diary.dto.GetDiariesResponse;
import ai.greenmate.greenmate_backend.domain.diary.repository.DiaryRepository;
import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import ai.greenmate.greenmate_backend.domain.greenmate.repository.GreenmateRepository;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.exception.GreenmateException;
import ai.greenmate.greenmate_backend.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {
  private final MemberRepository memberRepository;
  private final DiaryRepository diaryRepository;
  private final JwtService jwtService;
  private final GreenmateRepository greenmateRepository;

  public GetDiariesResponse getAllDiaries() {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));
    List<Greenmate> greenmates = greenmateRepository.findByMemberWithGreenmateInfoFetchJoin(member);
    List<DiaryDTO> diaryDTOs = diaryRepository.findByGreenmateIn(greenmates)
            .stream()
            .sorted((x, y) -> {
              if (x.getCreatedAt().isAfter(y.getCreatedAt())) return 1;
              else return -1;
            })
            .map(diary -> new DiaryDTO(diary.getId(), diary.getCreatedAt().toLocalDate(), diary.getContent()))
            .toList();
    return new GetDiariesResponse(diaryDTOs);

  }
}
