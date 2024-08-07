package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WateringResponse {
  private int water;
  private int energy;

  @Builder
  public WateringResponse(int energy, int water) {
    this.energy = energy;
    this.water = water;
  }

}
