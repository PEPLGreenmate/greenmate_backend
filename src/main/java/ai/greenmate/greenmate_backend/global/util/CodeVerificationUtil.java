package ai.greenmate.greenmate_backend.global.util;

import java.util.UUID;

public class CodeVerificationUtil {
  public static String generateRandomCode() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString().replace("-", "");
  }
}
