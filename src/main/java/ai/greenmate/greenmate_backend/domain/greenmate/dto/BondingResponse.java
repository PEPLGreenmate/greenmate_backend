package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BondingResponse {
  private int bond;
  private int energy;
  private int experience;

  @Builder
  public BondingResponse(int bond, int energy, int experience) {
    this.bond = bond;
    this.energy = energy;
    this.experience = experience;
  }
}
