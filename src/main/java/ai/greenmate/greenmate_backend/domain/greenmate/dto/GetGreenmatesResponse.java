package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetGreenmatesResponse {
  private List<GreenmateInfoDTO> greenmates;

  public GetGreenmatesResponse(List<GreenmateInfoDTO> greenmates) {
    this.greenmates = greenmates;
  }
}
