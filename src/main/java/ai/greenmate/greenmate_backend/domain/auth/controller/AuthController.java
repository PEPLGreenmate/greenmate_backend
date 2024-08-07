package ai.greenmate.greenmate_backend.domain.auth.controller;

import ai.greenmate.greenmate_backend.domain.auth.dto.LoginRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.LoginResponse;
import ai.greenmate.greenmate_backend.domain.auth.dto.ResetPasswordRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.SendCodeRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.SignupRequest;
import ai.greenmate.greenmate_backend.domain.auth.dto.VerifyCodeRequest;
import ai.greenmate.greenmate_backend.domain.auth.service.AuthService;
import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
    LoginResponse loginResponse = authService.login(loginRequest);
    return ResponseEntity.ok(new BaseResponse<>(loginResponse));
  }

  @PostMapping("/signup")
  public ResponseEntity<BaseResponse> sginup(@RequestBody SignupRequest signupRequest) {
    authService.signUp(signupRequest);
    return ResponseEntity.ok(new BaseResponse<>());
  }

  @PostMapping("/send-verification-code")
  public ResponseEntity<BaseResponse> sendVerificationCode(@RequestBody SendCodeRequest sendCodeRequest){
    authService.sendVerificationCode(sendCodeRequest);
    return ResponseEntity.ok(new BaseResponse<>());
  }

  @PostMapping("/verify-email-code")
  public ResponseEntity<BaseResponse> verfiyEmailCode(@RequestBody VerifyCodeRequest verifyCodeRequest){
    authService.verifyEmailCode(verifyCodeRequest);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PostMapping("/reset-password")
  public ResponseEntity<BaseResponse> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
    authService.resetPassword(resetPasswordRequest);
    return ResponseEntity.ok(new BaseResponse<>());
  }
}
