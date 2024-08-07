package ai.greenmate.greenmate_backend.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
  private String accessToken;

  public LoginResponse(String accessToken) {
    this.accessToken = accessToken;
  }
}
