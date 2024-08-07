package ai.greenmate.greenmate_backend.domain.auth.entity;

import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class VerificationCode extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String code;

  private VerificationCode(String email, String code) {
    this.email = email;
    this.code = code;
  }

  public static VerificationCode of(String email, String code) {
    return new VerificationCode(email, code);
  }
}
