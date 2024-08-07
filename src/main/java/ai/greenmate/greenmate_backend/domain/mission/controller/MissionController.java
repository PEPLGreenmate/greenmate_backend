package ai.greenmate.greenmate_backend.domain.mission.controller;

import ai.greenmate.greenmate_backend.domain.mission.dto.GetMissionDetailResponse;
import ai.greenmate.greenmate_backend.domain.mission.dto.GetMissionReviewResponse;
import ai.greenmate.greenmate_backend.domain.mission.dto.GetMissionsResponse;
import ai.greenmate.greenmate_backend.domain.mission.dto.PostReviewRequest;
import ai.greenmate.greenmate_backend.domain.mission.entity.MissionStatus;
import ai.greenmate.greenmate_backend.domain.mission.service.MissionService;
import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/missions")
@RestController
@RequiredArgsConstructor
public class MissionController {
  private final MissionService missionService;

  @GetMapping
  public ResponseEntity<BaseResponse> getMissions(@RequestParam("status") String status) {
    GetMissionsResponse getMissionsResponse = missionService.findMissions(MissionStatus.from(status));
    return ResponseEntity.ok(new BaseResponse(getMissionsResponse));
  }

  @GetMapping("/{missionId}")
  public ResponseEntity<BaseResponse> getMissionDetail(@PathVariable("missionId") long missionId) {
    GetMissionDetailResponse getMissionDetailResponse = missionService.findMissionDetail(missionId);
    return ResponseEntity.ok(new BaseResponse(getMissionDetailResponse));
  }

  @PostMapping("/accept/{missionId}")
  public ResponseEntity<BaseResponse> acceptMission(@PathVariable("missionId") long missionId) {
    missionService.acceptMission(missionId);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PostMapping("/complete/{missionId}")
  public ResponseEntity<BaseResponse> completeMission(@PathVariable("missionId") long missionId) {
    missionService.completeMission(missionId);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PostMapping("/cancel/{missionId}")
  public ResponseEntity<BaseResponse> cancelMission(@PathVariable("missionId") long missionId) {
    missionService.cancelMission(missionId);
    return ResponseEntity.ok(new BaseResponse());
  }

  @GetMapping("/review/{missionId}")
  public ResponseEntity<BaseResponse> getMissionReview(@PathVariable("missionId") long missionId) {
    GetMissionReviewResponse getMissionReviewResponse = missionService.findMissionReview(missionId);
    return ResponseEntity.ok(new BaseResponse<>(getMissionReviewResponse));
  }

  @PostMapping("/review/{missionId}")
  public ResponseEntity<BaseResponse> reviewMission(@PathVariable("missionId") long missionId, @RequestBody PostReviewRequest postReviewRequest) {
    missionService.reviewMission(missionId, postReviewRequest);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PatchMapping("/review/{missionId}")
  public ResponseEntity<BaseResponse> updateReview(@PathVariable("missionId") long missionId, @RequestBody PostReviewRequest postReviewRequest) {
    missionService.reviewMission(missionId, postReviewRequest);
    return ResponseEntity.ok(new BaseResponse());
  }
}
