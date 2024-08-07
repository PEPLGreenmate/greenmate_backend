package ai.greenmate.greenmate_backend.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyNicknameRequest {
  private String nickname;
}
