package ai.greenmate.greenmate_backend.global.exception.handler;

import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.exception.GreenmateException;
import ai.greenmate.greenmate_backend.global.exception.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GreenmateControllerAdvice {

  @ExceptionHandler(value = AuthException.class)
  public ResponseEntity<BaseResponse> handleNotFoundUser(GreenmateException e){
    log.info("handleNotFoundUser", e);
    return ResponseEntity.status(e.getBaseResponseStatus().getHttpStatus())
            .body(new BaseResponse<>(e.getBaseResponseStatus()));
  }

  @ExceptionHandler(value = GreenmateException.class)
  public ResponseEntity<BaseResponse> handleGreenamteException(GreenmateException e){
    log.info("handleGreenamteException", e);
    return ResponseEntity.status(e.getBaseResponseStatus().getHttpStatus())
            .body(new BaseResponse<>(e.getBaseResponseStatus()));
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<BaseResponse> handleException(Exception e){
    log.info("handleException", e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR));
  }
}
