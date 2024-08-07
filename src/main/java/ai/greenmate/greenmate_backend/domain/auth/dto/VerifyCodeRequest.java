package ai.greenmate.greenmate_backend.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyCodeRequest {
  private String email;
  private String code;
}
