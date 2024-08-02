package ai.greenmate.greenmate_backend.global.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {
  SUCCESS(true, HttpStatus.OK, 200, "요청에 성공하였습니다."),
  BAD_REQUEST(false, HttpStatus.BAD_REQUEST, 400, "입력값을 확인해주세요."),
  UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED, 401, "인증이 필요합니다."),
  FORBIDDEN(false, HttpStatus.FORBIDDEN, 403, "권한이 없습니다."),
  NOT_FOUND(false, HttpStatus.NOT_FOUND, 404, "대상을 찾을 수 없습니다."),
  INTERNAL_SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 내부에 오류가 발생했습니다."),

  OVER_DAILY_TRY_COUNT(false, HttpStatus.BAD_REQUEST, 4001, "인증 하루 시도 횟수를 초과하였습니다."),
  NOT_VALID_CODE(false, HttpStatus.BAD_REQUEST, 4002, "잘못된 코드입니다."),
  NOT_VALID_EMAIL(false, HttpStatus.BAD_REQUEST, 4003, "잘못된 이메일입니다."),
  NOT_ENOUGH_WATER(false,HttpStatus.BAD_REQUEST, 4004, "물 개수가 부족합니다."),
  NOT_ENOUGH_BOND(false,HttpStatus.BAD_REQUEST, 4005, "교감 개수가 부족합니다."),
  NOT_ENOUGH_ENERGY(false, HttpStatus.BAD_REQUEST, 4006, "교감하기에 에너지가 부족합니다."),
  DUPLICATE_NICKNAME(false, HttpStatus.BAD_REQUEST, 4007, "중복된 닉네임입니다.");

  private final boolean isSuccess;
  @JsonIgnore
  private final HttpStatus httpStatus;
  private final int code;
  private final String message;

  BaseResponseStatus(boolean isSuccess, HttpStatus httpStatus, int code, String message) {
    this.isSuccess = isSuccess;
    this.httpStatus = httpStatus;
    this.code = code;
    this.message = message;
  }
}
