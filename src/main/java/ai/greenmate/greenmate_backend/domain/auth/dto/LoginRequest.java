package ai.greenmate.greenmate_backend.domain.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
  private String email;
  private String password;
}
