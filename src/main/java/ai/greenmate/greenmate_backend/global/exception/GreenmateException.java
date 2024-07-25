package ai.greenmate.greenmate_backend.global.exception;

import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import lombok.Getter;

@Getter
public class GreenmateException extends RuntimeException {
  private final BaseResponseStatus baseResponseStatus;

  public GreenmateException(BaseResponseStatus baseResponseStatus) {
    this.baseResponseStatus = baseResponseStatus;
  }
}
