package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostGreenmatesRequest {
  private String name;
  private String expectation;
  private List<Long> characteristicIds;
}
