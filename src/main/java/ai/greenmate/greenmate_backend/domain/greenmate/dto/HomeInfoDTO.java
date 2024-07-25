package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HomeInfoDTO {
  private String greenmateName;
  private String imageUrl;
  private int experience;
  private int energy;
  private int bond;
  private int water;
  private int level;


}
