package ai.greenmate.greenmate_backend.global.exception.exception;

import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.exception.GreenmateException;

public class AuthException extends GreenmateException {
  public AuthException(BaseResponseStatus baseResponseStatus) {
    super(baseResponseStatus);
  }
}
