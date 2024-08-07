package ai.greenmate.greenmate_backend.domain.greenmate.controller;

import ai.greenmate.greenmate_backend.domain.greenmate.dto.BondingResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.dto.HomeInfoResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.dto.PostGreenmateRequest;
import ai.greenmate.greenmate_backend.domain.greenmate.dto.WateringResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.service.GreenmateService;
import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/greenmates")
@RestController
@RequiredArgsConstructor
public class GreenmateController {

  private final GreenmateService greenmateService;

  @PostMapping
  public ResponseEntity<BaseResponse> createGreenmate(@RequestBody PostGreenmateRequest postGreenmateRequest) {
    log.info("GreenmateController.createGreenmate : " + postGreenmateRequest);
    greenmateService.createGreenmate(postGreenmateRequest);
    return ResponseEntity.ok(new BaseResponse<>());
  }

  @GetMapping("/home")
  public ResponseEntity<BaseResponse<HomeInfoResponse>> getHome() {
    HomeInfoResponse homeInfo = greenmateService.findHomeInfo();
    return ResponseEntity.ok(new BaseResponse<>(homeInfo));
  }

  @PostMapping("/water/{greenmateId}")
  public ResponseEntity<BaseResponse> watering(@PathVariable("greenmateId") Long greenmateId) {
    WateringResponse wateringResponse = greenmateService.watering(greenmateId);
    return ResponseEntity.ok(new BaseResponse<>(wateringResponse));
  }

  @PostMapping("/bond/{greenmateId}")
  public ResponseEntity<BaseResponse> bonding(@PathVariable("greenmateId") Long greenmateId) {
    BondingResponse bondingResponse = greenmateService.bonding(greenmateId);
    return ResponseEntity.ok(new BaseResponse<>(bondingResponse));
  }
}
