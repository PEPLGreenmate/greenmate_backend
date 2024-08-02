package ai.greenmate.greenmate_backend.domain.member.controller;

import ai.greenmate.greenmate_backend.domain.member.dto.ChangeAddressRequest;
import ai.greenmate.greenmate_backend.domain.member.dto.ChangeLanguageRequest;
import ai.greenmate.greenmate_backend.domain.member.dto.ChangeNicknameRequest;
import ai.greenmate.greenmate_backend.domain.member.dto.VerifyNicknameRequest;
import ai.greenmate.greenmate_backend.domain.member.service.MemberService;
import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping("verify-nickname")
  public ResponseEntity<BaseResponse> verifyNickname(@RequestBody VerifyNicknameRequest verifyNicknameRequest) {
    log.info("verifyNickname {}", verifyNicknameRequest.getNickname());
    memberService.verifyNickname(verifyNicknameRequest);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PatchMapping("/nickname")
  public ResponseEntity<BaseResponse> changeNickname(@RequestBody ChangeNicknameRequest changeNicknameRequest) {
    memberService.changeNickname(changeNicknameRequest);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PatchMapping("/address")
  public ResponseEntity<BaseResponse> changeAddress(@RequestBody ChangeAddressRequest changeAddressRequest) {
    memberService.changeAddress(changeAddressRequest);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PatchMapping("/language")
  public ResponseEntity<BaseResponse> changeLanguage(@RequestBody ChangeLanguageRequest changeLanguageRequest) {
    memberService.changeLanguage(changeLanguageRequest);
    return ResponseEntity.ok(new BaseResponse());
  }
}
