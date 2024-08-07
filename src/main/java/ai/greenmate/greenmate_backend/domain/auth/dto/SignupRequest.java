package ai.greenmate.greenmate_backend.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
  private String email;
  private String password;
  private String nickname;
  private String gender;
  private String birthday;
  private String address;
  private String language;
}
