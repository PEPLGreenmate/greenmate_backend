package ai.greenmate.greenmate_backend.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendCodeRequest {
  private String email;
}
