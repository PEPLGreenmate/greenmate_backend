package ai.greenmate.greenmate_backend.domain.diary.controller;

import ai.greenmate.greenmate_backend.domain.diary.dto.GetDiariesResponse;
import ai.greenmate.greenmate_backend.domain.diary.service.DiaryService;
import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/diaries")
@RestController
@RequiredArgsConstructor
public class DiaryController {
  private final DiaryService diaryService;

  @GetMapping
  public ResponseEntity<BaseResponse> getDiaries(){
    GetDiariesResponse getDiariesResponse = diaryService.getAllDiaries();
    return ResponseEntity.ok(new BaseResponse(getDiariesResponse));
  }
}
