package ai.greenmate.greenmate_backend.domain.report.controller;


import ai.greenmate.greenmate_backend.domain.report.dto.GetEmotionMarbleDetailResponse;
import ai.greenmate.greenmate_backend.domain.report.dto.GetReportResponse;
import ai.greenmate.greenmate_backend.domain.report.dto.GetReportsMonthResponse;
import ai.greenmate.greenmate_backend.domain.report.service.ReportService;
import ai.greenmate.greenmate_backend.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reports")
@RestController
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;

  @GetMapping("/calander")
  public ResponseEntity<BaseResponse> getReportsMonth() {
    GetReportsMonthResponse getReportsMonthResponse = reportService.getAvailableMonth();
    return ResponseEntity.ok(new BaseResponse(getReportsMonthResponse));
  }

  @GetMapping
  public ResponseEntity<BaseResponse> getReports(@RequestParam String date) {
    GetReportResponse getReportResponse = reportService.findReports(date);
    return ResponseEntity.ok(new BaseResponse(getReportResponse));

  }

  @GetMapping("/emotion-marble")
  public ResponseEntity<BaseResponse> getEmotionMarbleDetail(@RequestParam String date) {
    GetEmotionMarbleDetailResponse getEmotionMarbleDetailResponse = reportService.findEmotionMarbleDetail(date);
    return ResponseEntity.ok(new BaseResponse(getEmotionMarbleDetailResponse));
  }
}
