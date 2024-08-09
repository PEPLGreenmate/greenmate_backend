package ai.greenmate.greenmate_backend.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetReportsMonthResponse {
  private List<String> dates;
}
