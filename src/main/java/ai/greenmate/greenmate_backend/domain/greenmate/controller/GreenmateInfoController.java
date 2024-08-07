package ai.greenmate.greenmate_backend.domain.greenmate.controller;

import ai.greenmate.greenmate_backend.domain.greenmate.dto.GetGreenmatesResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.service.GreenmateInfoService;
import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class GreenmateInfoController {

  private final GreenmateInfoService greenmateInfoService;

  @GetMapping("/greenmates")
  public ResponseEntity<BaseResponse<GetGreenmatesResponse>> getGreenmates(){
    return ResponseEntity.ok(new BaseResponse<>(greenmateInfoService.findAllGreenmates()));

  }
}
