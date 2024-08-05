package ai.greenmate.greenmate_backend.domain.member.service;

import ai.greenmate.greenmate_backend.domain.greenmate.repository.GreenmateRepository;
import ai.greenmate.greenmate_backend.domain.member.dto.ChangeAddressRequest;
import ai.greenmate.greenmate_backend.domain.member.dto.ChangeLanguageRequest;
import ai.greenmate.greenmate_backend.domain.member.dto.ChangeNicknameRequest;
import ai.greenmate.greenmate_backend.domain.member.dto.CurrentUserResponse;
import ai.greenmate.greenmate_backend.domain.member.dto.VerifyNicknameRequest;
import ai.greenmate.greenmate_backend.domain.member.entity.LanguageType;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.exception.GreenmateException;
import ai.greenmate.greenmate_backend.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final GreenmateRepository greenmateRepository;
  private final JwtService jwtService;

  public void verifyNickname(VerifyNicknameRequest verifyNicknameRequest) {
    boolean isExistNickname = memberRepository.existsByNickname(verifyNicknameRequest.getNickname());
    if(isExistNickname) {
      throw new GreenmateException(BaseResponseStatus.DUPLICATE_NICKNAME);
    }
  }
  public CurrentUserResponse findCurrentUser() {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));
    long greenmateCount = greenmateRepository.findByMemberWithGreenmateInfoFetchJoin(member).size();
    return CurrentUserResponse.from(member, greenmateCount);
  }

  @Transactional
  public void changeNickname(ChangeNicknameRequest changeNicknameRequest) {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));
    member.updateNickname(changeNicknameRequest.getNickname());
  }

  @Transactional
  public void changeAddress(ChangeAddressRequest changeAddressRequest) {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));
    member.updateAddress(changeAddressRequest.getAddress());
  }

  @Transactional
  public void changeLanguage(ChangeLanguageRequest changeLanguageRequest) {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));
    member.updateLanguage(LanguageType.valueOf(changeLanguageRequest.getLanguage().toUpperCase()));
  }
}
