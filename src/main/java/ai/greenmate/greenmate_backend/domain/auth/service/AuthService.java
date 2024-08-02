package ai.greenmate.greenmate_backend.domain.auth.service;

import ai.greenmate.greenmate_backend.domain.auth.dto.LoginRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.LoginResponse;
import ai.greenmate.greenmate_backend.domain.auth.dto.ResetPasswordRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.SendCodeRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.SignupRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.VerifyCodeRequest;
import ai.greenmate.greenmate_backend.domain.auth.entity.VerificationCode;
import ai.greenmate.greenmate_backend.domain.auth.repository.VerificationCodeRepository;
import ai.greenmate.greenmate_backend.domain.member.entity.Gender;
import ai.greenmate.greenmate_backend.domain.member.entity.LanguageType;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.email.EmailService;
import ai.greenmate.greenmate_backend.global.email.MailTemplate;
import ai.greenmate.greenmate_backend.global.exception.exception.AuthException;
import ai.greenmate.greenmate_backend.global.jwt.JwtService;
import ai.greenmate.greenmate_backend.global.util.CodeVerificationUtil;
import ai.greenmate.greenmate_backend.global.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
  private final JwtService jwtService;
  private final EmailService emailService;
  private final MemberRepository memberRepository;
  private final VerificationCodeRepository verificationCodeRepository;

  public LoginResponse login(LoginRequest loginRequest) {
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException(BaseResponseStatus.NOT_FOUND));

    checkPassword(member, password);

    String accessToken = jwtService.createAccessToken(email);
    return new LoginResponse(accessToken);
  }

  private void checkPassword(Member member, String password) {
    if (!member.getPassword().equals(password)) {
      throw new AuthException(BaseResponseStatus.BAD_REQUEST);
    }
  }

  public void signUp(SignupRequest signupRequest) {
    //TODO : ValidateSignupRequest

    Member member = Member.builder()
            .address(signupRequest.getAddress())
            .gender(Gender.valueOf(signupRequest.getGender()))
            .birthday(DateUtils.getLocalDate(signupRequest.getBirthday()))
            .nickname(signupRequest.getNickname())
            .password(signupRequest.getPassword())
            .email(signupRequest.getEmail())
            .language(LanguageType.KO) // TODO : 기본적으로 한국어? 아니면 입력받아서?
            .build();
    memberRepository.save(member);
  }

  public void sendVerificationCode(SendCodeRequest sendCodeRequest) {
    String email = sendCodeRequest.getEmail();
    String code = CodeVerificationUtil.generateRandomCode();
    List<VerificationCode> verificationCodes = verificationCodeRepository.findByEmail(email);
    long todayTryCount = verificationCodes.stream()
            .filter(verificationCode -> verificationCode.getCreatedAt().toLocalDate().equals(LocalDate.now()))
            .count();
    if (!inRangeValidTryCount(todayTryCount)) {
      throw new AuthException(BaseResponseStatus.OVER_DAILY_TRY_COUNT);
    }
      verificationCodeRepository.save(VerificationCode.of(email, code));
      emailService.sendSimpleMessage(getMailTemplate(email,code));
  }

  private boolean inRangeValidTryCount(long todayTryCount) {
    final int TRY_COUNT_THRESHHOLD = 5;
    return todayTryCount < TRY_COUNT_THRESHHOLD;
  }

  private MailTemplate getMailTemplate(String email, String code) {
    final String TITLE_FORMAT = "[그린메이트] 이메일 인증 코드 발송";
    final String CONTENT_FORMAT =
            "%s님, 안녕하세요.\n" +
                    "다음 코드를 사용하여 이메일 주소를 인증하세요.\n" +
                    "%s\n" +
                    "이메일 인증을 요청하지 않았다면 이 이메일을 무시하셔도 됩니다.\n" +
                    "감사합니다.\n" +
                    "[그린메이트]";
    MailTemplate mailTemplate = new MailTemplate();
    mailTemplate.setAddress(email);
    mailTemplate.setTitle(TITLE_FORMAT);
    mailTemplate.setContent(String.format(CONTENT_FORMAT, email, code));
    return mailTemplate;
  }

  public void verifyEmailCode(VerifyCodeRequest verifyCodeRequest) {
    String code = verifyCodeRequest.getCode();
    String email = verifyCodeRequest.getEmail();
    VerificationCode verificationCode = verificationCodeRepository.findByEmail(email)
            .stream()
            .max(Comparator.comparing(VerificationCode::getCreatedAt))
            .orElseThrow(() -> new AuthException(BaseResponseStatus.NOT_VALID_EMAIL));
    if (isValidCode(verificationCode, code)) {
      throw new AuthException(BaseResponseStatus.NOT_VALID_CODE);
    }
  }

  private static boolean isValidCode(VerificationCode verificationCode, String code) {
    return !verificationCode.getCode().equals(code);
  }

  public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
    String email = resetPasswordRequest.getEmail();
    String password = resetPasswordRequest.getPassword();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException(BaseResponseStatus.NOT_VALID_EMAIL));
    member.updatePassword(password);
  }
}
